-- 客户标签字典数据
-- 表：sys_dict(id, type, name, label, sort, remark)  主键 (type, name)
-- 业务表存 name（编码），label 为中文显示。
-- id 使用小整数区间（1000001+），避开框架雪花 id（约 6.9e17 起），保证不冲突；
-- ON DUPLICATE KEY UPDATE 使脚本可重复执行。
--
-- 与你 8 大类的对应关系：
--   (1) 客户意向标签            → CUSTOMER_INTENT
--   (2) 订单&交易属性            → ORDER_TYPE / PURCHASE_SCALE / TRADE_STATUS
--   (3) 产品标签                → PRODUCT_CATEGORY / CERTIFICATION
--   (4) 市场&区域标签           → TARGET_MARKET / CURRENCY_PREFERENCE / LOGISTICS_FOCUS
--   (5) 价格&谈判倾向           → PRICE_TENDENCY / NEGOTIATION_TERMS
--   (6) 风险&内部风控标签       → CUSTOMER_ROLE / RISK_FLAG
--   (7) 跟进渠道与来源          → ACQUISITION_CHANNEL / COMMUNICATION_LANGUAGE
--   (8) 客户特点                → CUSTOMER_TRAIT

INSERT INTO sys_dict (id, type, name, label, sort, remark) VALUES
-- ===== (1) 客户意向标签 =====
(1000001, 'CUSTOMER_INTENT', 'HIGH_INTENT',      '高意向',     1, '客户意向'),
(1000002, 'CUSTOMER_INTENT', 'MEDIUM_INTENT',    '中等意向',   2, '客户意向'),
(1000003, 'CUSTOMER_INTENT', 'LOW_INTENT',       '低意向',     3, '客户意向'),
(1000004, 'CUSTOMER_INTENT', 'DORMANT',          '沉睡',       4, '客户意向'),
(1000005, 'CUSTOMER_INTENT', 'NEW_INQUIRY',      '新询盘',     5, '客户意向'),
(1000006, 'CUSTOMER_INTENT', 'REPEAT_CUSTOMER',  '复购客户',   6, '客户意向'),

-- ===== (2) 订单&交易属性 =====
-- 订单类型
(1000011, 'ORDER_TYPE', 'SAMPLE',     '样品单',       1, '订单类型'),
(1000012, 'ORDER_TYPE', 'TRIAL',      '试订单',       2, '订单类型'),
(1000013, 'ORDER_TYPE', 'BULK',       '批量订单',     3, '订单类型'),
(1000014, 'ORDER_TYPE', 'LONG_TERM',  '长期合约客户', 4, '订单类型'),
(1000015, 'ORDER_TYPE', 'OEM',        'OEM',          5, '订单类型'),
(1000016, 'ORDER_TYPE', 'ODM',        'ODM',          6, '订单类型'),
(1000017, 'ORDER_TYPE', 'SPOT',       '现货采购',     7, '订单类型'),
-- 采购规模
(1000021, 'PURCHASE_SCALE', 'SMALL_BATCH',     '小批量',    1, '采购规模'),
(1000022, 'PURCHASE_SCALE', 'MID_LARGE',       '中大货',    2, '采购规模'),
(1000023, 'PURCHASE_SCALE', 'FCL',             '整柜FCL',   3, '采购规模'),
(1000024, 'PURCHASE_SCALE', 'LCL',             '拼箱LCL',   4, '采购规模'),
(1000025, 'PURCHASE_SCALE', 'MULTI_CATEGORY',  '多品类混采', 5, '采购规模'),
-- 交易状态
(1000031, 'TRADE_STATUS', 'DEAL_DONE',        '已成交',     1, '交易状态'),
(1000032, 'TRADE_STATUS', 'PENDING_SAMPLE',   '待打样',     2, '交易状态'),
(1000033, 'TRADE_STATUS', 'PENDING_QUOTE',    '待报价',     3, '交易状态'),
(1000034, 'TRADE_STATUS', 'PENDING_PI',       '待PI',       4, '交易状态'),
(1000035, 'TRADE_STATUS', 'PENDING_PAYMENT',  '待付款',     5, '交易状态'),
(1000036, 'TRADE_STATUS', 'PENDING_SHIP',     '待出货',     6, '交易状态'),
(1000037, 'TRADE_STATUS', 'AFTER_SALES',      '售后处理中', 7, '交易状态'),

-- ===== (3) 产品标签 =====
-- 产品品类
(1000041, 'PRODUCT_CATEGORY', 'HAND_DRYER',        '手持吹风机',            1, '产品品类'),
(1000042, 'PRODUCT_CATEGORY', 'COLD_AIR_DRYER',   '常温冷风吹风机',        2, '产品品类'),
(1000043, 'PRODUCT_CATEGORY', 'VACUUM',           '吸尘器',                3, '产品品类'),
(1000044, 'PRODUCT_CATEGORY', 'LITHIUM',           '锂电产品(18650/21700)', 4, '产品品类'),
(1000045, 'PRODUCT_CATEGORY', 'MULTI_PROCUREMENT', '多品类采购',            5, '产品品类'),
-- 认证关注（重点）
(1000051, 'CERTIFICATION', 'INMETRO', '巴西INMETRO', 1, '认证关注'),
(1000052, 'CERTIFICATION', 'CE',      '欧盟CE',      2, '认证关注'),
(1000053, 'CERTIFICATION', 'ROHS',    'ROHS',        3, '认证关注'),

-- ===== (4) 市场&区域标签 =====
-- 目标市场
(1000061, 'TARGET_MARKET', 'LATAM_MERCADO_LIBRE', '拉美市场（美客多）',     1, '目标市场'),
(1000062, 'TARGET_MARKET', 'MIDDLE_EAST_OSKAR',   '中东市场(Oskar渠道)',   2, '目标市场'),
(1000063, 'TARGET_MARKET', 'EU',                  '欧盟',                  3, '目标市场'),
(1000064, 'TARGET_MARKET', 'NORTH_AMERICA',       '北美',                  4, '目标市场'),
(1000065, 'TARGET_MARKET', 'OTHER_MARKET',        '其他',                  5, '目标市场'),
-- 币种偏好
(1000071, 'CURRENCY_PREFERENCE', 'MXN', '墨西哥比索', 1, '币种偏好'),
(1000072, 'CURRENCY_PREFERENCE', 'USD', '美元',       2, '币种偏好'),
(1000073, 'CURRENCY_PREFERENCE', 'EUR', '欧元',       3, '币种偏好'),
-- 物流关注点
(1000081, 'LOGISTICS_FOCUS', 'VGM',               '重视VGM',       1, '物流关注点'),
(1000082, 'LOGISTICS_FOCUS', 'SHIPPING_TIME',    '注重海运时效',   2, '物流关注点'),
(1000083, 'LOGISTICS_FOCUS', 'SENSITIVE_CARGO',  '敏感货顾虑',     3, '物流关注点'),

-- ===== (5) 价格&谈判倾向 =====
-- 价格倾向
(1000091, 'PRICE_TENDENCY', 'HIGH_BUDGET',         '高端预算',       1, '价格倾向'),
(1000092, 'PRICE_TENDENCY', 'COST_EFFECTIVE',      '性价比导向',     2, '价格倾向'),
(1000093, 'PRICE_TENDENCY', 'EXTREME_BARGAIN',     '极度压价型',     3, '价格倾向'),
(1000094, 'PRICE_TENDENCY', 'PRICE_INSENSITIVE',   '对价格不敏感',   4, '价格倾向'),
(1000095, 'PRICE_TENDENCY', 'LONG_TERM_NEGO',      '关注长期议价',   5, '价格倾向'),
-- 谈判条件
(1000101, 'NEGOTIATION_TERMS', 'PAYMENT_TERM',    '在意账期',           1, '谈判条件'),
(1000102, 'NEGOTIATION_TERMS', 'STRICT_MOQ',      '严格MOQ',            2, '谈判条件'),
(1000103, 'NEGOTIATION_TERMS', 'MOQ_FLEXIBLE',    '可接受起订量上调',   3, '谈判条件'),

-- ===== (6) 风险&内部风控标签 =====
-- 客户角色
(1000111, 'CUSTOMER_ROLE', 'INDIVIDUAL_BUYER',    '采购方个人决策',   1, '客户角色'),
(1000112, 'CUSTOMER_ROLE', 'FORMAL_PROCUREMENT',  '公司正规采购',     2, '客户角色'),
(1000113, 'CUSTOMER_ROLE', 'INTERMEDIARY',        '中间商(贸易商)',   3, '客户角色'),
(1000114, 'CUSTOMER_ROLE', 'RETAILER',            '终端零售商',       4, '客户角色'),
(1000115, 'CUSTOMER_ROLE', 'BRAND_OWNER',         '品牌方',           5, '客户角色'),
-- 风险标记
(1000121, 'RISK_FLAG', 'KICKBACK_RISK',     '需警惕回扣风险', 1, '风险标记'),
(1000122, 'RISK_FLAG', 'COMPLEX_DECISION',  '决策链复杂',     2, '风险标记'),
(1000123, 'RISK_FLAG', 'SLOW_APPROVAL',     '内部审批漫长',   3, '风险标记'),
(1000124, 'RISK_FLAG', 'CAUTIOUS_PAYMENT',  '付款记录谨慎',   4, '风险标记'),

-- ===== (7) 跟进渠道与来源 =====
-- 获客渠道
(1000131, 'ACQUISITION_CHANNEL', 'ALIBABA',         '阿里国际站',    1, '获客渠道'),
(1000132, 'ACQUISITION_CHANNEL', 'CANTON_FAIR',     '广交会',        2, '获客渠道'),
(1000133, 'ACQUISITION_CHANNEL', 'FB_INS',          'Facebook/Ins',  3, '获客渠道'),
(1000134, 'ACQUISITION_CHANNEL', 'OWNED_SITE',      '独立站',        4, '获客渠道'),
(1000135, 'ACQUISITION_CHANNEL', 'OFFLINE_REFERRAL','线下介绍',      5, '获客渠道'),
(1000136, 'ACQUISITION_CHANNEL', 'ALI_1688',        '1688',          6, '获客渠道'),
-- 沟通语言
(1000141, 'COMMUNICATION_LANGUAGE', 'ENGLISH',     '英语沟通',         1, '沟通语言'),
(1000142, 'COMMUNICATION_LANGUAGE', 'ENGLISH_DOC', '需要细致英文文档', 2, '沟通语言'),

-- ===== (8) 客户特点 =====
(1000151, 'CUSTOMER_TRAIT', 'RIGOROUS',            '严谨专业型',               1, '客户特点'),
(1000152, 'CUSTOMER_TRAIT', 'DECISIVE',            '爽快型',                   2, '客户特点'),
(1000153, 'CUSTOMER_TRAIT', 'HESITANT',            '犹豫纠结型',               3, '客户特点'),
(1000154, 'CUSTOMER_TRAIT', 'TECHNICAL',           '技术控，重视参数(RPM等)',  4, '客户特点'),
(1000155, 'CUSTOMER_TRAIT', 'DESIGN_FOCUSED',      '注重外观设计',             5, '客户特点'),
(1000156, 'CUSTOMER_TRAIT', 'COMPLIANCE_FOCUSED',  '重视认证合规',             6, '客户特点'),

-- ===== 沟通方式（跟进详情的「沟通方式」多选用） =====
(1000201, 'COMMUNICATION', 'PHONE',    '电话',       1, '沟通方式'),
(1000202, 'COMMUNICATION', 'EMAIL',    '邮件',       2, '沟通方式'),
(1000203, 'COMMUNICATION', 'WHATSAPP', 'WhatsApp',   3, '沟通方式'),
(1000204, 'COMMUNICATION', 'WECHAT',   'WeChat',     4, '沟通方式'),
(1000205, 'COMMUNICATION', 'VIDEO',    '视频会议',   5, '沟通方式'),
(1000206, 'COMMUNICATION', 'OFFLINE',  '线下拜访',   6, '沟通方式'),
(1000207, 'COMMUNICATION', 'SOCIAL',   '社交平台',   7, '沟通方式')

ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark);

-- 提示：sys_dict 由 DictService 内存缓存，执行后需重启应用 或 在「字典管理」页面任意保存一次触发 rebuild，新数据才会被下拉/组件读到。
