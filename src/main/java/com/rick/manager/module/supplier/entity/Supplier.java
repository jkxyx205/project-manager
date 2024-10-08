package com.rick.manager.module.supplier.entity;

import com.rick.db.dto.BaseCodeEntity;
import com.rick.db.plugin.dao.annotation.Column;
import com.rick.db.plugin.dao.annotation.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author Rick.Xu
 * @date 2024/9/8 18:17
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@Table(value = "t_supplier", comment = "供应商")
public class Supplier extends BaseCodeEntity<Long> {

    String name;

    String address;

    String contactName;

    String contactPhone;

    @Column(value = "attachment", comment = "附件")
    List<Map> attachmentList;

    @Column(columnDefinition = "text")
    String remark;

}