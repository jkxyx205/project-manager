package com.rick.manager.module.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rick.db.dto.type.BaseCodeDescriptionEntityWithLongId;
import com.rick.db.plugin.dao.annotation.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * @author Rick.Xu
 * @date 2024/2/20 15:46
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@Table(value = "sys_code_description", comment = "编号-描述 表")
public class CodeDescription extends BaseCodeDescriptionEntityWithLongId {

    CategoryEnum category;

    Integer sort;

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum CategoryEnum {
        /**
         * 分类
         */
        PRODUCT_CATEGORY("产品分类");

//        @JsonValue
        public String getCode() {
            return this.name();
        }

        public String getLabel() {
            return label;
        }

        private final String label;

        public static CategoryEnum valueOfCode(String code) {
            return valueOf(code);
        }
    }
}