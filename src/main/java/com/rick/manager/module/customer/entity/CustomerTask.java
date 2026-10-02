package com.rick.manager.module.customer.entity;

import com.rick.db.dto.BaseEntity;
import com.rick.db.plugin.dao.annotation.Column;
import com.rick.db.plugin.dao.annotation.OneToMany;
import com.rick.db.plugin.dao.annotation.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 客户计划任务
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
@Table(value = "t_customer_task", comment = "客户计划任务")
public class CustomerTask extends BaseEntity<Long> {

    /**
     * 关联 t_customer.id
     */
    Long instanceId;

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

    /**
     * 跟进明细
     */
    @OneToMany(subTable = "t_customer_task_detail", joinValue = "task_id", cascadeInsertOrUpdate = false, cascadeQuery = false)
    List<CustomerTaskDetail> details;
}
