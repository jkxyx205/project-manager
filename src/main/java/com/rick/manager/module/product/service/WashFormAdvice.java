package com.rick.manager.module.product.service;

import com.rick.formflow.form.service.FormAdvice;
import com.rick.formflow.form.service.bo.FormBO;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @author Rick.Xu
 * @date 2024/9/20 10:02
 */
@Component
public class WashFormAdvice implements FormAdvice {

    @Override
    public FormBO beforeRender(Map<String, ?> parameterMap, FormBO formBO) {
        if (Objects.equals(parameterMap.get("copy"), "true")) {
            formBO.getData().put("id", null);
            formBO.getData().put("instanceId", null);
            return new FormBO(formBO.getForm(), null, formBO.getPropertyList(), formBO.getData(), formBO.getFormAdvice());
        }

        return FormAdvice.super.beforeRender(parameterMap, formBO);
    }
}
