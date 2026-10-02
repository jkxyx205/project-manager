-- 跟进详情（t_customer_task）DDL —— 扁平结构，不再有嵌套明细表
-- 执行前请确认数据库连接（product-manager 库）
-- 增量迁移见 customer_followup_tab.sql

CREATE TABLE IF NOT EXISTS t_customer_task (
    id             bigint       NOT NULL AUTO_INCREMENT COMMENT '主键' PRIMARY KEY,
    instance_id    bigint       NULL COMMENT '关联 t_customer.id',
    type           varchar(16)  NOT NULL DEFAULT 'RECORD' COMMENT '详情类型 PLAN/RECORD',
    beijing_time   datetime     NULL COMMENT '北京时间',
    status         varchar(32)  NULL COMMENT '现阶段状态(STATUS 编码)',
    tags           text         NULL COMMENT '客户标签(CSV)',
    communication  text         NULL COMMENT '沟通方式(CSV)',
    description    text         NULL COMMENT '描述',
    completed      bit          NULL COMMENT '完成与否（仅对计划有意义）',
    create_by      bigint       NULL COMMENT '创建人',
    create_time    datetime     NULL COMMENT '创建时间',
    update_by      bigint       NULL COMMENT '更新人',
    update_time    datetime     NULL COMMENT '更新时间',
    is_deleted     bit          DEFAULT b'0' NOT NULL COMMENT '是否逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='跟进详情';
