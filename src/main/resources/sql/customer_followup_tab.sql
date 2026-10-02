-- 跟进记录 Tab 改造迁移
-- 1) t_customer 加客户标签列
-- 2) t_customer_task 演化为扁平跟进详情（加 type/beijing_time/status/tags/communication）
-- 3) 删除嵌套明细表 t_customer_task_detail
-- 4) COMMUNICATION 字典种子
-- 执行前请确认数据库连接（product-manager 库）

ALTER TABLE t_customer ADD COLUMN tags text NULL COMMENT '客户标签(CSV)';

ALTER TABLE t_customer_task
    ADD COLUMN type varchar(16) NOT NULL DEFAULT 'RECORD' COMMENT '详情类型 PLAN/RECORD',
    ADD COLUMN beijing_time datetime NULL COMMENT '北京时间',
    ADD COLUMN status varchar(32) NULL COMMENT '现阶段状态(STATUS 编码)',
    ADD COLUMN tags text NULL COMMENT '客户标签(CSV)',
    ADD COLUMN communication text NULL COMMENT '沟通方式(CSV)';

DROP TABLE IF EXISTS t_customer_task_detail;

-- COMMUNICATION 字典（沟通方式）种子见 sys_dict_customer_tags.sql（已包含），此处不再重复。

-- 提示：sys_dict 由 DictService 内存缓存，执行后需重启应用 或 在「字典管理」页面任意保存一次触发 rebuild。
