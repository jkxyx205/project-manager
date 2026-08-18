package com.rick.manager.module.customer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rick.formflow.form.service.FormAdvice;
import com.rick.formflow.form.service.bo.FormBO;
import com.rick.manager.module.code.service.CodeSequenceService;
import com.rick.manager.module.customer.dao.CustomerDAO;
import com.rick.manager.module.customer.entity.Customer;
import com.rick.manager.module.customer.entity.Record;
import com.rick.meta.dict.service.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @author Rick.Xu
 * @date 2024/9/11 06:14
 */
@Component
@RequiredArgsConstructor
public class CustomerFormAdvice implements FormAdvice {

    private final DictService dictService;

    private final CodeSequenceService codeSequenceService;

    private final CustomerDAO customerDAO;

    @Override
    public FormBO beforeRender(Map<String, ?> parameterMap, FormBO formBO) {
        if (Objects.isNull(formBO.getInstanceId())) {
            // GET DEFAULT CODE
            String code = codeSequenceService.getCodeSequence("CUSTOMER", "C");

            formBO.getPropertyData().put("code", code);
            FormBO.Property codeProperty = formBO.getPropertyList().stream().filter(property -> "code".equals(property.getName())).findFirst().get();
            codeProperty.setValue(code);

            Customer customer = Customer.builder().code(code).build();
            ObjectMapper objectMapper = new ObjectMapper();
            customer.setPurchasePlan(Record.builder().build());
            customer.setBackgroundInvestigation(Record.builder().build());

            Map<String, Object> newCustomer = objectMapper.convertValue(
                    customer,
                    new TypeReference<Map<String, Object>>() {}
            );

            formBO.getData().putAll(newCustomer);
        }

        return formBO;
    }


    @Override
    public void afterInstanceHandle(FormBO form, Long instanceId, Map<String, Object> values) {
        // rebuild dict
        dictService.rebuild("sys_dict_customer");
    }

    @Override
    public boolean insertOrUpdate(Map<String, Object> values) {
//        if (Objects.isNull(values.get("id"))) {
//            values.put("code", codeSequenceService.getCodeSequence("CUSTOMER", "C"));
//        } else {
//            values.put("code", "-1");
//        }

        customerDAO.insertOrUpdate(values);
        return true;
    }
}
