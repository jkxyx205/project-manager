package com.rick.manager.module.supplier.service;

import com.rick.formflow.form.service.FormAdvice;
import com.rick.formflow.form.service.bo.FormBO;
import com.rick.manager.module.code.service.CodeSequenceService;
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
public class SupplierFormAdvice implements FormAdvice {

    private final DictService dictService;

    private final CodeSequenceService codeSequenceService;

    @Override
    public FormBO beforeRender(Map<String, ?> parameterMap, FormBO formBO) {
        if (Objects.isNull(formBO.getInstanceId())) {
            // GET DEFAULT CODE（仅预览，不占用序号；保存时才真正递增）
            String code = codeSequenceService.peekCodeSequence("SUPPLIER", "S");

            formBO.getPropertyData().put("code", code);
            formBO.getPropertyList().stream()
                    .filter(p -> "code".equals(p.getName()))
                    .findFirst()
                    .ifPresent(p -> p.setValue(code));
        }

        return formBO;
    }

    @Override
    public void afterInstanceHandle(FormBO form, Long instanceId, Map<String, Object> values) {
        // rebuild dict
        dictService.rebuild("sys_dict_supplier");
    }

    @Override
    public boolean insertOrUpdate(Map<String, Object> values) {
        // 新增时真正占用序号；编辑时 code 不可改（updatable=false），无需处理
        if (Objects.isNull(values.get("id"))) {
            values.put("code", codeSequenceService.getCodeSequence("SUPPLIER", "S"));
        }

        // 返回 false，交给 formflow 默认 entityDAO 持久化
        return false;
    }
}
