package com.rick.manager.module.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rick.db.dto.BaseEntity;
import com.rick.db.plugin.dao.annotation.Column;
import com.rick.db.plugin.dao.annotation.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@Table(value = "t_record", comment = "记录")
public class Record extends BaseEntity<Long> {

    @Column(columnDefinition = "text")
    String content;

    Long instanceId;

    CategoryEnum category;

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum CategoryEnum {
        PURCHASE_PLAN("采购计划"),
        BACKGROUND_INVESTIGATION("背景记录");

        //        @JsonValue
        public String getCode() {
            return this.name();
        }

        public String getLabel() {
            return label;
        }

        private final String label;

        public static Record.CategoryEnum valueOfCode(String code) {
            return valueOf(code);
        }
    }
}
