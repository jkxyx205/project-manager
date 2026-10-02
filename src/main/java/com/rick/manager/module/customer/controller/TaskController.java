package com.rick.manager.module.customer.controller;

import com.rick.db.service.SharpService;
import com.rick.db.service.support.Params;
import com.rick.manager.module.customer.dao.CustomerTaskDAO;
import com.rick.manager.module.customer.dao.CustomerTaskDetailDAO;
import com.rick.manager.module.customer.entity.CustomerTask;
import com.rick.manager.module.customer.entity.CustomerTaskDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 跟进面板：按客户分组展示「未完成计划任务」下的「未完成明细」，支持标记完成、跳转客户编辑页。
 *
 * @author Rick.Xu
 * @date 2026/9/30
 */
@Slf4j
@Controller
@RequestMapping("tasks")
public class TaskController {

    private final SharpService sharpService;
    private final CustomerTaskDAO taskDAO;
    private final CustomerTaskDetailDAO detailDAO;

    public TaskController(SharpService sharpService, CustomerTaskDAO taskDAO,
                          CustomerTaskDetailDAO detailDAO) {
        this.sharpService = sharpService;
        this.taskDAO = taskDAO;
        this.detailDAO = detailDAO;
    }

    /**
     * 查询所有「未完成跟进」下的「未完成明细」，按客户→跟进分组。
     * 列别名用下划线，NestedRowMapper 会驼峰映射到 TaskDetailRow。
     */
    private static final String SQL_UNCOMPLETED =
            "SELECT c.id AS customer_id, c.name AS customer_name, " +
            "fu.id AS task_id, fu.description AS task_description, " +
            "d.id AS detail_id, DATE_FORMAT(d.occur_date, '%Y-%m-%d') AS occur_date, " +
            "d.description AS detail_description " +
            "FROM t_customer_task_detail d " +
            "JOIN t_customer_task fu ON fu.id = d.task_id AND fu.is_deleted = 0 " +
            "JOIN t_customer c ON c.id = fu.instance_id AND c.is_deleted = 0 " +
            "WHERE d.is_deleted = 0 " +
            "AND (d.completed IS NULL OR d.completed = 0) " +
            "AND (fu.completed IS NULL OR fu.completed = 0) " +
            "ORDER BY c.id, fu.id, d.id";

    @GetMapping
    public String gotoTask(Model model) {
        List<TaskDetailRow> rows = sharpService.query(SQL_UNCOMPLETED, Collections.emptyMap(), TaskDetailRow.class);

        Map<Long, CustomerGroup> custMap = new LinkedHashMap<>();
        Map<Long, TaskGroup> fuMap = new LinkedHashMap<>();
        for (TaskDetailRow r : rows) {
            CustomerGroup cg = custMap.computeIfAbsent(r.customerId, k -> {
                CustomerGroup g = new CustomerGroup();
                g.customerId = k;
                g.customerName = r.customerName;
                return g;
            });
            TaskGroup fg = fuMap.computeIfAbsent(r.taskId, k -> {
                TaskGroup g = new TaskGroup();
                g.taskId = k;
                g.description = r.taskDescription;
                cg.tasks.add(g);
                return g;
            });
            DetailItem d = new DetailItem();
            d.detailId = r.detailId;
            d.occurDate = r.occurDate;
            d.description = r.detailDescription;
            fg.details.add(d);
        }

        Long customerFormId = sharpService.queryForObject(
                "select id from sys_form where code = :code",
                Params.builder(1).pv("code", "t_customer").build(), Long.class).orElse(null);

        model.addAttribute("groups", new ArrayList<>(custMap.values()));
        model.addAttribute("customerFormId", customerFormId);
        return "task";
    }

    /**
     * 标记明细完成；若该跟进下已无未完成明细，则一并标记跟进完成。
     */
    @PostMapping("{detailId}/complete")
    @ResponseBody
    public Map<String, Object> completeDetail(@PathVariable Long detailId) {
        Optional<CustomerTaskDetail> opt = detailDAO.selectById(detailId);
        if (!opt.isPresent()) {
            return Collections.singletonMap("success", false);
        }
        CustomerTaskDetail d = opt.get();
        d.setCompleted(true);
        detailDAO.update(d);

        List<CustomerTaskDetail> remain = detailDAO.selectByTaskIds(Collections.singleton(d.getTaskId()));
        boolean anyUncompleted = remain.stream().anyMatch(x -> !Boolean.TRUE.equals(x.getCompleted()));
        if (!anyUncompleted) {
            Optional<CustomerTask> fuOpt = taskDAO.selectById(d.getTaskId());
            if (fuOpt.isPresent() && !Boolean.TRUE.equals(fuOpt.get().getCompleted())) {
                CustomerTask fu = fuOpt.get();
                fu.setCompleted(true);
                taskDAO.update(fu);
            }
        }

        return Collections.singletonMap("success", true);
    }

    // ===== 分组 DTO =====

    @Getter
    @Setter
    public static class TaskDetailRow {
        Long customerId;
        String customerName;
        Long taskId;
        String taskDescription;
        Long detailId;
        String occurDate;
        String detailDescription;
    }

    @Getter
    @Setter
    public static class CustomerGroup {
        Long customerId;
        String customerName;
        List<TaskGroup> tasks = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class TaskGroup {
        Long taskId;
        String description;
        List<DetailItem> details = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class DetailItem {
        Long detailId;
        String occurDate;
        String description;
    }
}
