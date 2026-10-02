package com.rick.manager.module.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rick.db.dto.BaseEntity;
import com.rick.db.plugin.dao.annotation.Column;
import com.rick.db.plugin.dao.annotation.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * 计划任务明细
 *
 * @author Rick.Xu
 * @date 2026/9/30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@Table(value = "t_customer_task_detail", comment = "计划任务明细")
public class CustomerTaskDetail extends BaseEntity<Long> {

    /**
     * 关联 t_customer_task.id
     */
    Long taskId;

    /**
     * 日期（选填）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate occurDate;

    /**
     * 描述（必填）
     */
    @NotBlank
    @Column(columnDefinition = "text")
    String description;

    /**
     * 完成与否
     */
    Boolean completed;
}
