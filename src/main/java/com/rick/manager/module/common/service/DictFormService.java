package com.rick.manager.module.common.service;

import com.rick.db.plugin.SQLUtils;
import com.rick.formflow.form.service.FormAdvice;
import com.rick.formflow.form.service.FormUtils;
import com.rick.formflow.form.service.bo.FormBO;
import com.rick.meta.dict.service.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @author Rick.Xu
 * @date 2024/9/3 14:54
 */
@Component
@RequiredArgsConstructor
public class DictFormService implements FormAdvice {

    private final DictService dictService;

    @Override
    public void beforeInstanceHandle(FormBO form, Long instanceId, Map<String, Object> values) {
        values.put("name", String.valueOf(values.get("name")).toUpperCase());
    }

    @Override
    public void afterInstanceHandle(FormBO form, Long instanceId, Map<String, Object> values) {
        dictService.rebuild((String) values.get("type"));
        dictService.rebuild("sys_dict_category");

        if (Objects.equals(values.get("type"), "PRODUCT_TYPE")) {
            FormUtils.update(864275104153022464L, null);
        }
    }


    @Override
    public void beforeDeleteInstance(Long instanceId) {
        // TODO 检查 是否被引用过，引用过的不能删除
    }

    @Override
    public void afterDeleteInstance(Long instanceId) {
        // 强制物理删除
        SQLUtils.delete("sys_dict", new Object[]{instanceId}, "id = ?");

        dictService.rebuild("PRODUCT_TYPE");
        dictService.rebuild("sys_dict_category");

        FormUtils.update(864275104153022464L, null);
    }

}
