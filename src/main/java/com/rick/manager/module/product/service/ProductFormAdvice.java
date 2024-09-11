package com.rick.manager.module.product.service;

import com.rick.formflow.form.cpn.core.Form;
import com.rick.formflow.form.service.FormAdvice;
import com.rick.formflow.form.service.bo.FormBO;
import com.rick.meta.dict.entity.Dict;
import com.rick.meta.dict.service.DictService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @author Rick.Xu
 * @date 2024/9/8 21:12
 */
@Service
@RequiredArgsConstructor
public class ProductFormAdvice implements FormAdvice {

    private final DictService dictService;

    @Override
    public void beforeInstanceHandle(FormBO form, Long instanceId, Map<String, Object> values) {
//        FormAdvice.super.beforeInstanceHandle(form, instanceId, values);
//        String supplierId = (String) values.get("supplierId");
//        values.put("supplier", Supplier.builder().id(Long.parseLong(supplierId)).build());
    }

    @Override
    public void beforeGetInstance(Form form, Long instanceId, Map<String, Object> values) {
        String categoryCode = (String) values.get("category_code");
        if (StringUtils.isNotBlank(categoryCode)) {
            Optional<Dict> optional = dictService.getDictByTypeAndName("PRODUCT_TYPE", categoryCode);
            optional.ifPresent(dict -> {
                values.put("attrFormId", dict.getRemark());
            });
        }
    }
}
