-- t_country 时区字段改造 DDL
-- 执行前请确认数据库连接（product-manager 库）
-- timezone 存 IANA 时区 ID，如 'Asia/Tokyo'、'America/New_York'、'Asia/Shanghai'

ALTER TABLE t_country ADD COLUMN timezone VARCHAR(64) NULL COMMENT 'IANA 时区 ID，如 Asia/Tokyo';

-- 亚洲（含中国所在的 Asia/Shanghai）
UPDATE t_country SET timezone = 'Asia/Shanghai'    WHERE cname = '中国';
UPDATE t_country SET timezone = 'Asia/Hong_Kong'   WHERE cname = '中国香港';
UPDATE t_country SET timezone = 'Asia/Macau'       WHERE cname = '中国澳门';
UPDATE t_country SET timezone = 'Asia/Taipei'      WHERE cname = '中国台湾';
UPDATE t_country SET timezone = 'Asia/Tokyo'       WHERE cname = '日本';
UPDATE t_country SET timezone = 'Asia/Seoul'       WHERE cname = '韩国';
UPDATE t_country SET timezone = 'Asia/Pyongyang'   WHERE cname = '朝鲜';
UPDATE t_country SET timezone = 'Asia/Singapore'   WHERE cname = '新加坡';
UPDATE t_country SET timezone = 'Asia/Bangkok'     WHERE cname = '泰国';
UPDATE t_country SET timezone = 'Asia/Ho_Chi_Minh' WHERE cname = '越南';
UPDATE t_country SET timezone = 'Asia/Phnom_Penh'  WHERE cname = '柬埔寨';
UPDATE t_country SET timezone = 'Asia/Vientiane'  WHERE cname = '老挝';
UPDATE t_country SET timezone = 'Asia/Rangoon'     WHERE cname = '缅甸';
UPDATE t_country SET timezone = 'Asia/Kuala_Lumpur' WHERE cname = '马来西亚';
UPDATE t_country SET timezone = 'Asia/Manila'      WHERE cname = '菲律宾';
UPDATE t_country SET timezone = 'Asia/Jakarta'     WHERE cname = '印度尼西亚';
UPDATE t_country SET timezone = 'Asia/Brunei'      WHERE cname = '文莱';
UPDATE t_country SET timezone = 'Asia/Dhaka'       WHERE cname = '孟加拉国';
UPDATE t_country SET timezone = 'Asia/Kolkata'     WHERE cname = '印度';
UPDATE t_country SET timezone = 'Asia/Karachi'      WHERE cname = '巴基斯坦';
UPDATE t_country SET timezone = 'Asia/Kabul'       WHERE cname = '阿富汗';
UPDATE t_country SET timezone = 'Asia/Tehran'       WHERE cname = '伊朗';
UPDATE t_country SET timezone = 'Asia/Baghdad'      WHERE cname = '伊拉克';
UPDATE t_country SET timezone = 'Asia/Riyadh'      WHERE cname = '沙特阿拉伯';
UPDATE t_country SET timezone = 'Asia/Dubai'       WHERE cname = '阿联酋';
UPDATE t_country SET timezone = 'Asia/Jerusalem'   WHERE cname = '以色列';
UPDATE t_country SET timezone = 'Asia/Damascus'    WHERE cname = '叙利亚';
UPDATE t_country SET timezone = 'Asia/Amman'       WHERE cname = '约旦';
UPDATE t_country SET timezone = 'Asia/Beirut'      WHERE cname = '黎巴嫩';
UPDATE t_country SET timezone = 'Asia/Yerevan'      WHERE cname = '亚美尼亚';
UPDATE t_country SET timezone = 'Asia/Baku'         WHERE cname = '阿塞拜疆';
UPDATE t_country SET timezone = 'Asia/Tbilisi'     WHERE cname = '格鲁吉亚';
UPDATE t_country SET timezone = 'Asia/Almaty'       WHERE cname = '哈萨克斯坦';
UPDATE t_country SET timezone = 'Asia/Tashkent'    WHERE cname = '乌兹别克斯坦';
UPDATE t_country SET timezone = 'Asia/Bishkek'     WHERE cname = '吉尔吉斯斯坦';
UPDATE t_country SET timezone = 'Asia/Dushanbe'    WHERE cname = '塔吉克斯坦';
UPDATE t_country SET timezone = 'Asia/Ashgabat'    WHERE cname = '土库曼斯坦';
UPDATE t_country SET timezone = 'Asia/Kathmandu'   WHERE cname = '尼泊尔';
UPDATE t_country SET timezone = 'Asia/Colombo'     WHERE cname = '斯里兰卡';
UPDATE t_country SET timezone = 'Asia/Ulaanbaatar' WHERE cname = '蒙古';

-- 欧洲
UPDATE t_country SET timezone = 'Europe/London'    WHERE cname = '英国';
UPDATE t_country SET timezone = 'Europe/Paris'     WHERE cname = '法国';
UPDATE t_country SET timezone = 'Europe/Berlin'    WHERE cname = '德国';
UPDATE t_country SET timezone = 'Europe/Rome'      WHERE cname = '意大利';
UPDATE t_country SET timezone = 'Europe/Madrid'     WHERE cname = '西班牙';
UPDATE t_country SET timezone = 'Europe/Lisbon'    WHERE cname = '葡萄牙';
UPDATE t_country SET timezone = 'Europe/Amsterdam' WHERE cname = '荷兰';
UPDATE t_country SET timezone = 'Europe/Brussels'  WHERE cname = '比利时';
UPDATE t_country SET timezone = 'Europe/Vienna'    WHERE cname = '奥地利';
UPDATE t_country SET timezone = 'Europe/Zurich'    WHERE cname = '瑞士';
UPDATE t_country SET timezone = 'Europe/Stockholm' WHERE cname = '瑞典';
UPDATE t_country SET timezone = 'Europe/Oslo'      WHERE cname = '挪威';
UPDATE t_country SET timezone = 'Europe/Copenhagen' WHERE cname = '丹麦';
UPDATE t_country SET timezone = 'Europe/Helsinki'  WHERE cname = '芬兰';
UPDATE t_country SET timezone = 'Europe/Warsaw'    WHERE cname = '波兰';
UPDATE t_country SET timezone = 'Europe/Prague'    WHERE cname = '捷克';
UPDATE t_country SET timezone = 'Europe/Bratislava' WHERE cname = '斯洛伐克';
UPDATE t_country SET timezone = 'Europe/Budapest'  WHERE cname = '匈牙利';
UPDATE t_country SET timezone = 'Europe/Bucharest' WHERE cname = '罗马尼亚';
UPDATE t_country SET timezone = 'Europe/Sofia'     WHERE cname = '保加利亚';
UPDATE t_country SET timezone = 'Europe/Athens'    WHERE cname = '希腊';
UPDATE t_country SET timezone = 'Europe/Belgrade'  WHERE cname = '塞尔维亚';
UPDATE t_country SET timezone = 'Europe/Moscow'    WHERE cname = '俄罗斯';
UPDATE t_country SET timezone = 'Europe/Kiev'      WHERE cname = '乌克兰';
UPDATE t_country SET timezone = 'Europe/Minsk'     WHERE cname = '白俄罗斯';
UPDATE t_country SET timezone = 'Europe/Dublin'    WHERE cname = '爱尔兰';
UPDATE t_country SET timezone = 'Europe/Reykjavik' WHERE cname = '冰岛';

-- 非洲
UPDATE t_country SET timezone = 'Africa/Cairo'     WHERE cname = '埃及';
UPDATE t_country SET timezone = 'Africa/Tripoli'   WHERE cname = '利比亚';
UPDATE t_country SET timezone = 'Africa/Tunis'     WHERE cname = '突尼斯';
UPDATE t_country SET timezone = 'Africa/Algiers'   WHERE cname = '阿尔及利亚';
UPDATE t_country SET timezone = 'Africa/Casablanca' WHERE cname = '摩洛哥';
UPDATE t_country SET timezone = 'Africa/Lagos'     WHERE cname = '尼日利亚';
UPDATE t_country SET timezone = 'Africa/Johannesburg' WHERE cname = '南非';
UPDATE t_country SET timezone = 'Africa/Nairobi'   WHERE cname = '肯尼亚';
UPDATE t_country SET timezone = 'Africa/Addis_Ababa' WHERE cname = '埃塞俄比亚';
UPDATE t_country SET timezone = 'Africa/Khartoum'  WHERE cname = '苏丹';

-- 大洋洲
UPDATE t_country SET timezone = 'Australia/Sydney'  WHERE cname = '澳大利亚';
UPDATE t_country SET timezone = 'Pacific/Auckland'  WHERE cname = '新西兰';
UPDATE t_country SET timezone = 'Pacific/Fiji'      WHERE cname = '斐济';

-- 北美洲
UPDATE t_country SET timezone = 'America/New_York'  WHERE cname = '美国';
UPDATE t_country SET timezone = 'America/Toronto'   WHERE cname = '加拿大';
UPDATE t_country SET timezone = 'America/Mexico_City' WHERE cname = '墨西哥';
UPDATE t_country SET timezone = 'America/Havana'    WHERE cname = '古巴';
UPDATE t_country SET timezone = 'America/Panama'    WHERE cname = '巴拿马';
UPDATE t_country SET timezone = 'America/Guatemala' WHERE cname = '危地马拉';

-- 南美洲
UPDATE t_country SET timezone = 'America/Sao_Paulo' WHERE cname = '巴西';
UPDATE t_country SET timezone = 'America/Argentina/Buenos_Aires' WHERE cname = '阿根廷';
UPDATE t_country SET timezone = 'America/Santiago'  WHERE cname = '智利';
UPDATE t_country SET timezone = 'America/Lima'      WHERE cname = '秘鲁';
UPDATE t_country SET timezone = 'America/Bogota'    WHERE cname = '哥伦比亚';
UPDATE t_country SET timezone = 'America/Caracas'   WHERE cname = '委内瑞拉';

-- 提示：如 t_country.cname 与上述不一致，请按实际值调整 WHERE 条件；
-- 未覆盖的国家请在表中补全 timezone 后即自动生效，无需改动 Java 代码。
