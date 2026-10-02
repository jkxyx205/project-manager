-- 客户计划任务改造 DDL
-- 执行前请确认数据库连接（product-manager 库）

-- 计划任务主表
CREATE TABLE IF NOT EXISTS t_customer_task (
    id          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键' PRIMARY KEY,
    instance_id bigint       NULL COMMENT '关联 t_customer.id',
    description text         NULL COMMENT '描述',
    completed   bit          NULL COMMENT '完成与否',
    create_by   bigint       NULL COMMENT '创建人',
    create_time datetime     NULL COMMENT '创建时间',
    update_by   bigint       NULL COMMENT '更新人',
    update_time datetime     NULL COMMENT '更新时间',
    is_deleted  bit          DEFAULT b'0' NOT NULL COMMENT '是否逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户计划任务';

-- 计划任务明细表
CREATE TABLE IF NOT EXISTS t_customer_task_detail (
    id          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键' PRIMARY KEY,
    task_id bigint       NULL COMMENT '关联 t_customer_task.id',
    occur_date  date         NULL COMMENT '日期',
    description text         NULL COMMENT '描述',
    completed   bit          NULL COMMENT '完成与否',
    create_by   bigint       NULL COMMENT '创建人',
    create_time datetime     NULL COMMENT '创建时间',
    update_by   bigint       NULL COMMENT '更新人',
    update_time datetime     NULL COMMENT '更新时间',
    is_deleted  bit          DEFAULT b'0' NOT NULL COMMENT '是否逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='计划任务明细';

-- 删除旧的「详细计划任务」文本列（旧数据将丢失）
ALTER TABLE t_customer DROP COLUMN details;
