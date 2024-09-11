package com.rick.manager.module.product.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rick.common.http.json.deserializer.EntityWithCodePropertyDeserializer;
import com.rick.common.http.json.deserializer.EntityWithLongIdPropertyDeserializer;
import com.rick.db.dto.BaseCodeEntity;
import com.rick.db.plugin.dao.annotation.Column;
import com.rick.db.plugin.dao.annotation.Embedded;
import com.rick.db.plugin.dao.annotation.ManyToOne;
import com.rick.db.plugin.dao.annotation.Table;
import com.rick.manager.module.supplier.entity.Supplier;
import com.rick.meta.config.validator.DictValueCheck;
import com.rick.meta.dict.model.DictType;
import com.rick.meta.dict.model.DictValue;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author Rick.Xu
 * @date 2024/9/8 20:14
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@Table(value = "t_product", comment = "产品")
public class Product extends BaseCodeEntity<Long> {

    @Column(comment = "品名")
    String name;

    @Embedded(columnPrefix="category_")
    @JsonDeserialize(using = EntityWithCodePropertyDeserializer.class)
    @DictType(type = "PRODUCT_TYPE")
    @DictValueCheck(type = "PRODUCT_TYPE")
    DictValue category;

    @ManyToOne(value = "supplier_id", parentTable = "t_supplier", updatable = false, comment = "所属供应商")
//    @JsonAlias("supplierId")
    @JsonDeserialize(using = EntityWithLongIdPropertyDeserializer.class)
    Supplier supplier;

    BigDecimal rmbPrice;

    BigDecimal usdPrice;

    @Column(comment = "备注")
    String remark;

    Long instanceId;

    Long attrInstanceId;

    @Column(value = "picture", columnDefinition = "text", comment = "图片")
    List<HashMap<String, String>> pictures;

//    @ManyToMany(thirdPartyTable = "t_product_accessory", referenceTable =  "t_product", referenceColumnName = "accessory_id", columnDefinition="product_id")
//    List<Product> accessoryList;
    List<List<String>> accessoryList;
}