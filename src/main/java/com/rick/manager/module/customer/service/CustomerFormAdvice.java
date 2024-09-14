package com.rick.manager.module.customer.service;

import com.rick.formflow.form.service.FormAdvice;
import com.rick.formflow.form.service.bo.FormBO;
import com.rick.meta.dict.service.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Rick.Xu
 * @date 2024/9/11 06:14
 */
@Component
@RequiredArgsConstructor
public class CustomerFormAdvice implements FormAdvice {

    private final DictService dictService;

    @Override
    public void afterInstanceHandle(FormBO form, Long instanceId, Map<String, Object> values) {
        // rebuild dict
        dictService.rebuild("sys_dict_customer");
    }
}
