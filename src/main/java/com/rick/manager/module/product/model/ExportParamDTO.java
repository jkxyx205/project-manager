package com.rick.manager.module.product.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Rick.Xu
 * @date 2024/9/11 16:49
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ExportParamDTO {

    Long customerId;

    Long productId;

    PriceTypeEnum priceType;

    String incoterm;

    BigDecimal rate;

    String step;

    Integer quantity;

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum PriceTypeEnum {
        RMB(0, "人民币含税"),
        USD(1, "美金未税");
        private static final Map<Integer, PriceTypeEnum> codeMap = new HashMap<>();

        static {
            for (PriceTypeEnum e : values()) {
                codeMap.put(e.code, e);
            }
        }

        private final int code;
        private final String label;

        public int getCode() {
            return this.code;
        }

        /**
         * code枚举 必须重写toString()
         *
         * @return
         */
        @Override
        public String toString() {
            return String.valueOf(code);
        }

        public static PriceTypeEnum valueOfCode(int code) {
            return codeMap.get(code);
        }
    }
}