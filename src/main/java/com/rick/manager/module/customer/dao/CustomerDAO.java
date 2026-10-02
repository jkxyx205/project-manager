package com.rick.manager.module.customer.dao;

import com.rick.common.util.Maps;
import com.rick.db.plugin.dao.core.EntityCodeDAOImpl;
import com.rick.manager.module.customer.entity.Customer;
import com.rick.manager.module.customer.entity.CustomerTask;
import com.rick.manager.module.customer.entity.CustomerTaskDetail;
import com.rick.manager.module.customer.entity.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Rick.Xu
 * @date 2024-09-14 14:27:32
 */
@Repository
@RequiredArgsConstructor
public class CustomerDAO extends EntityCodeDAOImpl<Customer, Long> {

    final RecordDAO recordDAO;
    final CustomerTaskDAO taskDAO;
    final CustomerTaskDetailDAO detailDAO;

    @Override
    public int insertOrUpdate(Customer customer) {
        int rows = super.insertOrUpdate(customer);
        Record purchasePlan = customer.getPurchasePlan();
        purchasePlan.setInstanceId(customer.getId());

        Record backgroundInvestigation = customer.getBackgroundInvestigation();
        backgroundInvestigation.setInstanceId(customer.getId());

        if (!recordDAO.existsByParams(Maps.of("instanceId", customer.getId(), "category", purchasePlan.getCategory(), "content", purchasePlan.getContent()), "instance_id = :instanceId and category = :category and content = :content and is_deleted = 0")) {
            recordDAO.delete(customer.getId(), purchasePlan.getCategory());
            recordDAO.insert(purchasePlan);
        }

        if (!recordDAO.existsByParams(Maps.of("instanceId", customer.getId(),"category", backgroundInvestigation.getCategory(), "content", backgroundInvestigation.getContent()), "instance_id = :instanceId and category = :category and content = :content and is_deleted = 0")) {
            recordDAO.delete(customer.getId(), backgroundInvestigation.getCategory());
            recordDAO.insert(backgroundInvestigation);
        }

        saveTasks(customer.getId(), customer.getTasks());

        return rows;
    }

    @Override
    public Optional<Customer> selectById(Long id) {
        Optional<Customer> optional = super.selectById(id);
        if (optional.isPresent()) {
            Record purchasePlan = recordDAO.selectByInstanceId(id, Record.CategoryEnum.PURCHASE_PLAN);
            Record backgroundInvestigation = recordDAO.selectByInstanceId(id, Record.CategoryEnum.BACKGROUND_INVESTIGATION);
            Customer customer = optional.get();
            customer.setPurchasePlan(purchasePlan);
            customer.setBackgroundInvestigation(backgroundInvestigation);

            customer.setTasks(loadTasks(id));
        }
        return optional;
    }

    /**
     * 加载某客户的计划任务（含明细），并解析创建人/更新人姓名
     */
    private List<CustomerTask> loadTasks(Long customerId) {
        List<CustomerTask> tasks = taskDAO.selectByInstanceId(customerId);
        if (tasks.isEmpty()) {
            return tasks;
        }

        List<Long> taskIds = tasks.stream().map(CustomerTask::getId).collect(Collectors.toList());
        List<CustomerTaskDetail> allDetails = detailDAO.selectByTaskIds(taskIds);
        Map<Long, List<CustomerTaskDetail>> detailMap = allDetails.stream()
                .collect(Collectors.groupingBy(CustomerTaskDetail::getTaskId));
        for (CustomerTask fu : tasks) {
            fu.setDetails(detailMap.getOrDefault(fu.getId(), Collections.emptyList()));
        }

        return tasks;
    }

    /**
     * diff 式保存计划任务及其明细：新增 insert、既有 update、被移除的逻辑删除
     */
    private void saveTasks(Long customerId, List<CustomerTask> tasks) {
        if (tasks == null) {
            tasks = Collections.emptyList();
        }

        List<CustomerTask> existing = taskDAO.selectByInstanceId(customerId);
        Set<Long> submittedIds = tasks.stream()
                .map(CustomerTask::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        for (CustomerTask fu : existing) {
            if (!submittedIds.contains(fu.getId())) {
                detailDAO.deleteLogicallyByTaskId(fu.getId());
                taskDAO.deleteLogicallyById(fu.getId());
            }
        }

        for (CustomerTask fu : tasks) {
            fu.setInstanceId(customerId);
            if (fu.getId() == null) {
                taskDAO.insert(fu);
            } else {
                taskDAO.update(fu);
            }
            saveDetails(fu.getId(), fu.getDetails());
        }
    }

    private void saveDetails(Long taskId, List<CustomerTaskDetail> details) {
        if (details == null) {
            details = Collections.emptyList();
        }

        List<CustomerTaskDetail> existing = detailDAO.selectByTaskIds(Collections.singleton(taskId));
        Set<Long> submittedIds = details.stream()
                .map(CustomerTaskDetail::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        for (CustomerTaskDetail d : existing) {
            if (!submittedIds.contains(d.getId())) {
                detailDAO.deleteLogicallyById(d.getId());
            }
        }

        for (CustomerTaskDetail d : details) {
            d.setTaskId(taskId);
            if (d.getId() == null) {
                detailDAO.insert(d);
            } else {
                detailDAO.update(d);
            }
        }
    }
}
