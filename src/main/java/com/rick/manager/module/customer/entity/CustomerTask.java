package com.rick.manager.module.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rick.db.dto.BaseEntity;
import com.rick.db.plugin.dao.annotation.Column;
import com.rick.db.plugin.dao.annotation.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 跟进详情（计划 / 记录）
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
@Table(value = "t_customer_task", comment = "跟进详情")
public class CustomerTask extends BaseEntity<Long> {

    /**
     * 关联 t_customer.id
     */
    Long instanceId;

    /**
     * 详情类型：PLAN / RECORD
     */
    String type;

    /**
     * 北京时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime beijingTime;

    /**
     * 现阶段状态（STATUS 字典编码）
     */
    String status;

    /**
     * 客户标签（CSV，CUSTOMER_TAG 字典编码）
     */
    @Column(columnDefinition = "text")
    String tags;

    /**
     * 沟通方式（CSV，COMMUNICATION 字典编码）
     */
    @Column(columnDefinition = "text")
    String communication;

    /**
     * 描述（必填）
     */
    @NotBlank
    @Column(columnDefinition = "text")
    String description;

    /**
     * 完成与否（仅对计划有意义）
     */
    Boolean completed;
}
