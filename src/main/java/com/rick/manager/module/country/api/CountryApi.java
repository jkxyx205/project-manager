package com.rick.manager.module.country.api;

import com.rick.db.service.SharpService;
import com.rick.db.service.support.Params;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * 国家相关数据接口：按国家 id 查询 IANA 时区（t_country.timezone），
 * 供前端 JS 由「北京时间」换算「客户时间」。
 *
 * @author Rick.Xu
 * @date 2026/10/02
 */
@RestController
@RequestMapping("country")
@RequiredArgsConstructor
public class CountryApi {

    private static final String SQL_TIMEZONE = "SELECT timezone FROM t_country WHERE id = :id";

    private final SharpService sharpService;

    /**
     * 返回 {timezone: "Asia/Tokyo"} 形式；未配置时 timezone 为 null。
     */
    @GetMapping("timezone")
    public Map<String, Object> timezone(@RequestParam("id") Long id) {
        String tz = sharpService.queryForObject(
                SQL_TIMEZONE,
                Params.builder(1).pv("id", id).build(),
                String.class).orElse(null);
        return Collections.singletonMap("timezone", tz);
    }
}
