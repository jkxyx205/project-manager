package com.rick.manager.module.customer.entity;

import com.rick.db.dto.BaseCodeEntity;
import com.rick.db.plugin.dao.annotation.Column;
import com.rick.db.plugin.dao.annotation.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * @author Rick.Xu
 * @date 2024/9/14 14:22
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@Table(value = "t_customer", comment = "客户")
public class Customer extends BaseCodeEntity<Long> {

    String name;

    String address;

    String contactName;

    String whatsApp;

    @Column(columnDefinition = "text", comment = "备注")
    String remark;

}