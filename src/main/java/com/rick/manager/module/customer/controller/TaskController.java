package com.rick.manager.module.customer.controller;

import com.rick.db.service.SharpService;
import com.rick.db.service.support.Params;
import com.rick.manager.module.customer.dao.CustomerTaskDAO;
import com.rick.manager.module.customer.entity.CustomerTask;
import com.rick.meta.dict.entity.Dict;
import com.rick.meta.dict.service.DictService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 跟进面板：以表格统一展示每个客户的跟进摘要（最近一次记录 + 下一次计划）。
 *
 * @author Rick.Xu
 * @date 2026/9/30
 */
@Slf4j
@Controller
@RequestMapping("tasks")
public class TaskController {

    private static final ZoneId BEIJING_ZONE = ZoneId.of("Asia/Shanghai");
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final SharpService sharpService;
    private final CustomerTaskDAO taskDAO;
    private final DictService dictService;

    public TaskController(SharpService sharpService, CustomerTaskDAO taskDAO, DictService dictService) {
        this.sharpService = sharpService;
        this.taskDAO = taskDAO;
        this.dictService = dictService;
    }

    /** 有跟进任务的客户（含国家、时区） */
    private static final String SQL_CUSTOMERS =
            "SELECT c.id, c.code, c.name, c.contact_name, c.status, " +
            "co.cname AS country_name, co.timezone AS country_timezone " +
            "FROM t_customer c " +
            "LEFT JOIN t_country co ON co.id = c.national_code " +
            "WHERE c.is_deleted = 0 " +
            "AND EXISTS (SELECT 1 FROM t_customer_task t WHERE t.instance_id = c.id AND t.is_deleted = 0) " +
            "ORDER BY c.code";

    /** 全部跟进详情（未删） */
    private static final String SQL_TASKS =
            "SELECT instance_id, type, beijing_time, communication, description, completed " +
            "FROM t_customer_task " +
            "WHERE is_deleted = 0 " +
            "ORDER BY instance_id, beijing_time";

    @GetMapping
    public String gotoTask(Model model) {
        List<CustomerWithCountry> customers = sharpService.query(SQL_CUSTOMERS, Collections.emptyMap(), CustomerWithCountry.class);
        List<TaskRaw> tasks = sharpService.query(SQL_TASKS, Collections.emptyMap(), TaskRaw.class);

        Map<String, String> statusLabels = toLabelMap("STATUS");
        Map<String, String> commLabels = toLabelMap("COMMUNICATION");

        Map<Long, List<TaskRaw>> tasksByCustomer = tasks.stream()
                .collect(Collectors.groupingBy(TaskRaw::getInstanceId, LinkedHashMap::new, Collectors.toList()));

        List<FollowupPanelRow> rows = new ArrayList<>();
        for (CustomerWithCountry c : customers) {
            List<TaskRaw> cTasks = tasksByCustomer.getOrDefault(c.id, Collections.emptyList());

            // 全部跟进（含计划/记录）按北京时间升序，用于定位「最近一次记录」及其前一条
            List<TaskRaw> withTimeAsc = cTasks.stream()
                    .filter(t -> t.beijingTime != null)
                    .sorted((a, b) -> a.beijingTime.compareTo(b.beijingTime))
                    .collect(Collectors.toList());

            // 最近一次记录 = 升序遍历中最后遇到的 RECORD；其前一条跟进（任意类型）用于算间隔
            TaskRaw latestRecord = null;
            TaskRaw prevBeforeLatest = null;
            for (int i = 0; i < withTimeAsc.size(); i++) {
                TaskRaw t = withTimeAsc.get(i);
                if ("RECORD".equals(t.type)) {
                    prevBeforeLatest = (i > 0) ? withTimeAsc.get(i - 1) : null;
                    latestRecord = t;
                }
            }

            // 下一次计划：未完成的 PLAN 中 beijing_time 最小
            TaskRaw nextPlan = cTasks.stream()
                    .filter(t -> "PLAN".equals(t.type) && !Boolean.TRUE.equals(t.completed) && t.beijingTime != null)
                    .min((a, b) -> a.beijingTime.compareTo(b.beijingTime))
                    .orElse(null);

            FollowupPanelRow row = new FollowupPanelRow();
            row.customerId = c.id;
            row.code = c.code;
            row.name = c.name;
            row.countryName = c.countryName;
            row.contactName = c.contactName;
            row.statusLabel = c.status == null ? "—" : statusLabels.getOrDefault(c.status, c.status);

            if (latestRecord != null) {
                row.latestCommunication = csvToLabels(latestRecord.communication, commLabels);
                row.latestTimeStr = latestRecord.beijingTime.format(FMT);
            }
            // 跟进间隔时间 = 最近一次记录 − 其前一条跟进（天数，向上取整），与详情页算法一致
            if (latestRecord != null && prevBeforeLatest != null) {
                long ms = Duration.between(prevBeforeLatest.beijingTime, latestRecord.beijingTime).toMillis();
                row.intervalDays = String.valueOf((long) Math.ceil(ms / 86400000.0));
            }

            if (nextPlan != null) {
                String desc = nextPlan.description == null || nextPlan.description.trim().isEmpty() ? "无" : nextPlan.description.trim();
                row.nextPlanDescription = desc;
                row.nextTimeChinaStr = nextPlan.beijingTime.format(FMT);
                row.nextTimeCountryStr = formatCountryTime(nextPlan.beijingTime, c.countryTimezone);
            } else {
                row.nextPlanDescription = "无";
            }

            rows.add(row);
        }

        Long customerFormId = sharpService.queryForObject(
                "select id from sys_form where code = :code",
                Params.builder(1).pv("code", "t_customer").build(), Long.class).orElse(null);

        model.addAttribute("rows", rows);
        model.addAttribute("customerFormId", customerFormId);
        return "task";
    }

    /** 标记某条「计划」类跟进完成。 */
    @PostMapping("{taskId}/complete")
    @ResponseBody
    public Map<String, Object> complete(@PathVariable Long taskId) {
        Optional<CustomerTask> opt = taskDAO.selectById(taskId);
        if (!opt.isPresent()) {
            return Collections.singletonMap("success", false);
        }
        CustomerTask task = opt.get();
        task.setCompleted(true);
        taskDAO.update(task);
        return Collections.singletonMap("success", true);
    }

    private Map<String, String> toLabelMap(String type) {
        List<Dict> list = dictService.getDictByType(type);
        Map<String, String> map = new HashMap<>();
        if (list != null) {
            for (Dict d : list) {
                map.put(d.getName(), d.getLabel());
            }
        }
        return map;
    }

    private static String csvToLabels(String csv, Map<String, String> labelMap) {
        if (csv == null || csv.trim().isEmpty()) {
            return "—";
        }
        List<String> labels = new ArrayList<>();
        for (String s : csv.split(",")) {
            String v = s.trim();
            if (!v.isEmpty()) {
                labels.add(labelMap.getOrDefault(v, v));
            }
        }
        return labels.isEmpty() ? "—" : String.join("、", labels);
    }

    private static String formatCountryTime(LocalDateTime beijingTime, String timezone) {
        if (beijingTime == null || timezone == null || timezone.trim().isEmpty()) {
            return "—";
        }
        try {
            ZonedDateTime countryTime = beijingTime.atZone(BEIJING_ZONE).withZoneSameInstant(ZoneId.of(timezone));
            return countryTime.format(FMT);
        } catch (Exception e) {
            return "—";
        }
    }

    // ===== DTO =====

    @Getter
    @Setter
    public static class CustomerWithCountry {
        Long id;
        String code;
        String name;
        String contactName;
        String status;
        String countryName;
        String countryTimezone;
    }

    @Getter
    @Setter
    public static class TaskRaw {
        Long instanceId;
        String type;
        LocalDateTime beijingTime;
        String communication;
        String description;
        Boolean completed;
    }

    @Getter
    @Setter
    public static class FollowupPanelRow {
        Long customerId;
        String code;
        String name;
        String countryName;
        String contactName;
        String statusLabel;
        String latestCommunication = "—";
        String latestTimeStr = "—";
        String intervalDays = "—";
        String nextPlanDescription = "无";
        String nextTimeChinaStr = "—";
        String nextTimeCountryStr = "—";
    }
}
