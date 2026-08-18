package com.rick.manager.module.customer.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rick.common.http.json.deserializer.EntityWithCodePropertyDeserializer;
import com.rick.db.dto.BaseCodeEntity;
import com.rick.db.plugin.dao.annotation.Column;
import com.rick.db.plugin.dao.annotation.Embedded;
import com.rick.db.plugin.dao.annotation.OneToMany;
import com.rick.db.plugin.dao.annotation.Table;
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

    /**
     * 客户名称
     */
    String name;

    /**
     * 地址
     */
    String address;

    /**
     * 邮编
     */
    String postcode;

    /**
     * 国家
     */
    @Embedded(columnPrefix="national_")
    @JsonDeserialize(using = EntityWithCodePropertyDeserializer.class)
    @DictType(type = "t_country")
    DictValue national;

    /**
     * 联系人姓名
     */
    String contactName;

    /**
     * 联系人职位
     */
    String contactTitle;

    /**
     * 联系人电话/WhatApp
     */
    String contactPhone;

    /**
     * 联系人邮箱
     */
    String contactMail;

    /**
     * 公司网站
     */
    String website;

    // 社交媒体

    /**
     * Facebook
     */
    String facebook;

    /**
     * Instagram
     */
    String instagram;

    /**
     * YouTube
     */
    String youTube;

    /**
     * Linkedin
     */
    String linkedin;

    /**
     * 客户类型
     */
    String customerType;

    /**
     * 来源
     */
    String source;

    /**
     * 客户级别
     */
    String level;

    /**
     * 客户需求
     */
    @Column(columnDefinition = "text")
    String requirement;

    /**
     * 采购计划
     */
    @OneToMany(subTable = "t_record", joinValue = "instance_id", oneToOne = true, cascadeInsertOrUpdate = false, cascadeQuery = false)
    Record purchasePlan;

    /**
     * 背景调查
     */
    @OneToMany(subTable = "t_record", joinValue = "instance_id", oneToOne = true, cascadeInsertOrUpdate = false, cascadeQuery = false)
    Record backgroundInvestigation;

    /**
     * 现阶段状态
     */
    String status;

    @Column(columnDefinition = "text", comment = "备注")
    String remark;

    @Column(columnDefinition = "text", comment = "详细跟进记录")
    String details;
}