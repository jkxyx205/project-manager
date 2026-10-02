package com.rick.manager.module.country.service;

import com.rick.db.service.SharpService;
import com.rick.db.service.support.Params;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 国家时区换算：基于 t_country.timezone（IANA 时区 ID），
 * 将北京时间（墙钟，按 Asia/Shanghai 解释）转换为对应国家的本地时间。
 *
 * @author Rick.Xu
 * @date 2026/10/02
 */
@Service
public class CountryTimeService {

    private static final ZoneId BEIJING_ZONE = ZoneId.of("Asia/Shanghai");

    private static final String SQL_TIMEZONE =
            "SELECT timezone FROM t_country WHERE id = :id";

    private final SharpService sharpService;

    public CountryTimeService(SharpService sharpService) {
        this.sharpService = sharpService;
    }

    /**
     * 将北京时间换算为指定国家的本地时间。
     *
     * @param beijingTime 北京时间（墙钟，按 Asia/Shanghai 解释）
     * @param countryId   t_country 主键
     * @return 该国所在时区的本地时间
     * @throws IllegalArgumentException 国家不存在，或尚未配置时区
     */
    public ZonedDateTime convert(LocalDateTime beijingTime, Long countryId) {
        String zoneId = sharpService.queryForObject(
                SQL_TIMEZONE,
                Params.builder(1).pv("id", countryId).build(),
                String.class).orElse(null);
        if (zoneId == null || zoneId.trim().isEmpty()) {
            throw new IllegalArgumentException("国家 " + countryId + " 不存在或未配置时区");
        }
        return beijingTime.atZone(BEIJING_ZONE).withZoneSameInstant(ZoneId.of(zoneId));
    }
}
