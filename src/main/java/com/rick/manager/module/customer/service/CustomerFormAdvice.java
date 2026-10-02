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

import java.util.ArrayList;
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
            // GET DEFAULT CODE（仅预览，不占用序号；保存时才真正递增）
            String code = codeSequenceService.peekCodeSequence("CUSTOMER", "C");

            formBO.getPropertyData().put("code", code);
            FormBO.Property codeProperty = formBO.getPropertyList().stream().filter(property -> "code".equals(property.getName())).findFirst().get();
            codeProperty.setValue(code);

            Customer customer = Customer.builder().code(code).build();
            ObjectMapper objectMapper = new ObjectMapper();
            customer.setPurchasePlan(Record.builder().build());
            customer.setBackgroundInvestigation(Record.builder().build());
            customer.setTasks(new ArrayList<>());

            Map<String, Object> newCustomer = objectMapper.convertValue(
                    customer,
                    new TypeReference<Map<String, Object>>() {}
            );

            formBO.getData().putAll(newCustomer);
            // 记录预览编号，供保存时判断用户是否手动修改过
            formBO.getData().put("previewCode", code);
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
        if (Objects.isNull(values.get("id"))) {
            // 新增：若用户未手动修改编号（提交值仍等于预览值），则真正占用序号；
            // 若手动改过，则使用用户输入的编号，不占用序号
            Object submittedCode = values.get("code");
            Object previewCode = values.get("previewCode");
            if (submittedCode == null || submittedCode.equals(previewCode)) {
                values.put("code", codeSequenceService.getCodeSequence("CUSTOMER", "C"));
            }
        }
        values.remove("previewCode");

        customerDAO.insertOrUpdate(values);
        return true;
    }
}
