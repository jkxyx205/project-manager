package com.rick.manager.module.customer.dao;

import com.rick.common.util.Maps;
import com.rick.db.plugin.dao.core.EntityCodeDAOImpl;
import com.rick.db.service.SharpService;
import com.rick.db.service.support.Params;
import com.rick.manager.module.customer.entity.Customer;
import com.rick.manager.module.customer.entity.CustomerTask;
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
    final SharpService sharpService;

    @Override
    public int insertOrUpdate(Customer customer) {
        // 保存前：用跟进详情同步客户的现阶段状态与客户标签
        syncFromTasks(customer);

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
     * 加载某客户的跟进详情（扁平列表）
     */
    private List<CustomerTask> loadTasks(Long customerId) {
        return taskDAO.selectByInstanceId(customerId);
    }

    /**
     * diff 式保存跟进详情：新增 insert、既有 update、被移除的逻辑删除
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
        }
    }

    /**
     * 用跟进详情同步客户字段：
     * - 现阶段状态：取最近一条「记录」（按北京时间倒序）的 status 覆盖；无则不动。
     * - 客户标签：旧值(DB) ∪ 表单提交值 ∪ 全部跟进详情标签，单调累加、不删除。
     */
    private void syncFromTasks(Customer customer) {
        List<CustomerTask> tasks = customer.getTasks() == null ? Collections.emptyList() : customer.getTasks();

        // 现阶段状态：最近一条 RECORD
        tasks.stream()
                .filter(t -> "RECORD".equals(t.getType())
                        && t.getBeijingTime() != null
                        && t.getStatus() != null && !t.getStatus().trim().isEmpty())
                .max(Comparator.comparing(CustomerTask::getBeijingTime))
                .ifPresent(t -> customer.setStatus(t.getStatus()));

        // 客户标签：单调累加
        Set<String> tagSet = new LinkedHashSet<>();
        if (customer.getId() != null) {
            String oldTags = sharpService.queryForObject(
                    "select tags from t_customer where id = :id",
                    Params.builder(1).pv("id", customer.getId()).build(),
                    String.class).orElse(null);
            addCsv(tagSet, oldTags);
        }
        addCsv(tagSet, customer.getTags());
        tasks.forEach(t -> addCsv(tagSet, t.getTags()));
        customer.setTags(tagSet.isEmpty() ? null : String.join(",", tagSet));
    }

    private static void addCsv(Set<String> target, String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            return;
        }
        for (String s : csv.split(",")) {
            String v = s.trim();
            if (!v.isEmpty()) {
                target.add(v);
            }
        }
    }
}
