package com.rick.manager.module.customer.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rick.common.http.json.deserializer.EntityWithCodePropertyDeserializer;
import com.rick.db.dto.BaseCodeEntity;
import com.rick.db.plugin.dao.annotation.Column;
import com.rick.db.plugin.dao.annotation.Embedded;
import com.rick.db.plugin.dao.annotation.Table;
import com.rick.meta.config.validator.DictValueCheck;
import com.rick.meta.dict.model.DictType;
import com.rick.meta.dict.model.DictValue;
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

    @Embedded(columnPrefix="national_")
    @JsonDeserialize(using = EntityWithCodePropertyDeserializer.class)
    @DictType(type = "t_country")
    @DictValueCheck(type = "t_country")
    DictValue national;

    String contactPhone;

    String contactName;

    String contactMail;

    String position;

    String website;

    String whatsApp;

    @Column(columnDefinition = "text", comment = "备注")
    String remark;

}