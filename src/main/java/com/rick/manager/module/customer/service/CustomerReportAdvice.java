package com.rick.manager.module.customer.service;

import com.rick.db.service.SharpService;
import com.rick.db.service.support.Params;
import com.rick.manager.module.common.service.OperatorReportAdvice;
import com.rick.meta.dict.entity.Dict;
import com.rick.meta.dict.service.DictService;
import com.rick.report.core.entity.Report;
import com.rick.report.core.service.ReportAdvice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 客户列表报表增强：参考「跟进面板」（TaskController），
 * 为当页客户行补充所属阶段与跟进摘要字段（最近一次记录、跟进间隔、下一次计划）。
 *
 * @author Rick.Xu
 * @date 2026/10/26
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerReportAdvice implements ReportAdvice {

    private static final ZoneId BEIJING_ZONE = ZoneId.of("Asia/Shanghai");
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** 指定客户的跟进详情（未删），与跟进面板 SQL_TASKS 一致 */
    private static final String SQL_TASKS =
            "SELECT instance_id, type, beijing_time, communication, description, completed " +
            "FROM t_customer_task " +
            "WHERE is_deleted = 0 AND instance_id IN (:ids) " +
            "ORDER BY instance_id, beijing_time";

    private static final String SQL_COUNTRIES = "SELECT id, cname, timezone FROM t_country WHERE id IN (:ids)";

    private final SharpService sharpService;
    private final DictService dictService;
    private final OperatorReportAdvice operatorReportAdvice;

    @Override
    public void beforeSetRow(Report report, List<Map<String, Object>> rows, Map<String, Object> requestMap) {
        operatorReportAdvice.beforeSetRow(report, rows, requestMap);
        if (rows == null || rows.isEmpty()) {
            return;
        }

        List<Long> customerIds = rows.stream()
                .map(row -> toLong(row.get("id")))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (customerIds.isEmpty()) {
            return;
        }

        List<TaskRaw> tasks = sharpService.query(SQL_TASKS, Params.builder(1).pv("ids", customerIds).build(), TaskRaw.class);
        Map<Long, List<TaskRaw>> tasksByCustomer = tasks.stream()
                .collect(Collectors.groupingBy(TaskRaw::getInstanceId, LinkedHashMap::new, Collectors.toList()));

        // 国家：id 字符串 -> (cname, timezone)，national_code 为 varchar，统一按字符串匹配
        List<Long> countryIds = rows.stream()
                .map(row -> toLong(row.get("national")))
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        Map<String, Country> countryMap = new HashMap<>();
        if (!countryIds.isEmpty()) {
            for (Country c : sharpService.query(SQL_COUNTRIES, Params.builder(1).pv("ids", countryIds).build(), Country.class)) {
                countryMap.put(String.valueOf(c.getId()), c);
            }
        }

        Map<String, String> statusLabels = toLabelMap("STATUS");
        Map<String, String> commLabels = toLabelMap("COMMUNICATION");

        for (Map<String, Object> row : rows) {
            // 所属阶段：STATUS 字典 label，缺失时回退原编码（与面板一致）
            Object status = row.get("status");
            row.put("status", status == null ? "—" : statusLabels.getOrDefault(String.valueOf(status), String.valueOf(status)));

            List<TaskRaw> cTasks = tasksByCustomer.getOrDefault(toLong(row.get("id")), Collections.emptyList());

            // 全部跟进（含计划/记录）按北京时间升序，用于定位「最近一次记录」及其前一条
            List<TaskRaw> withTimeAsc = cTasks.stream()
                    .filter(t -> t.getBeijingTime() != null)
                    .sorted((a, b) -> a.getBeijingTime().compareTo(b.getBeijingTime()))
                    .collect(Collectors.toList());

            TaskRaw latestRecord = null;
            TaskRaw prevBeforeLatest = null;
            for (int i = 0; i < withTimeAsc.size(); i++) {
                TaskRaw t = withTimeAsc.get(i);
                if ("RECORD".equals(t.getType())) {
                    prevBeforeLatest = (i > 0) ? withTimeAsc.get(i - 1) : null;
                    latestRecord = t;
                }
            }

            // 下一次计划：未完成的 PLAN 中 beijing_time 最小
            TaskRaw nextPlan = cTasks.stream()
                    .filter(t -> "PLAN".equals(t.getType()) && !Boolean.TRUE.equals(t.getCompleted()) && t.getBeijingTime() != null)
                    .min((a, b) -> a.getBeijingTime().compareTo(b.getBeijingTime()))
                    .orElse(null);

            row.put("latestCommunication", latestRecord == null ? "—" : csvToLabels(latestRecord.getCommunication(), commLabels));
            row.put("latestFollowupTime", latestRecord == null ? "—" : latestRecord.getBeijingTime().format(FMT));

            // 跟进间隔时间 = 最近一次记录 − 其前一条跟进（天数，向上取整），与面板算法一致
            String intervalDays = "—";
            if (latestRecord != null && prevBeforeLatest != null) {
                long ms = Duration.between(prevBeforeLatest.getBeijingTime(), latestRecord.getBeijingTime()).toMillis();
                intervalDays = String.valueOf((long) Math.ceil(ms / 86400000.0));
            }
            row.put("intervalDays", intervalDays);

            Country country = row.get("national") == null ? null : countryMap.get(String.valueOf(toLong(row.get("national"))));
            if (nextPlan != null) {
                String desc = nextPlan.getDescription() == null || nextPlan.getDescription().trim().isEmpty() ? "无" : nextPlan.getDescription().trim();
                row.put("nextPlanDescription", desc);

                String chinaStr = nextPlan.getBeijingTime().format(FMT);
                String countryStr = formatCountryTime(nextPlan.getBeijingTime(), country == null ? null : country.getTimezone());
                row.put("nextPlanTime", country == null || country.getCname() == null || "—".equals(countryStr)
                        ? chinaStr
                        : chinaStr + " (" + country.getCname() + " " + countryStr + ")");
            } else {
                row.put("nextPlanDescription", "无");
                row.put("nextPlanTime", "—");
            }
        }
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

    private static Long toLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        try {
            String s = String.valueOf(value).trim();
            return s.isEmpty() ? null : Long.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // ===== DTO =====

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
    public static class Country {
        Long id;
        String cname;
        String timezone;
    }
}
