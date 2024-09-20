package com.rick.manager.module.product.service;

import com.rick.common.util.JsonUtils;
import com.rick.formflow.form.cpn.core.Form;
import com.rick.formflow.form.service.FormAdvice;
import com.rick.formflow.form.service.FormConstants;
import com.rick.formflow.form.service.bo.FormBO;
import com.rick.manager.common.SSLSocketClient;
import com.rick.meta.dict.entity.Dict;
import com.rick.meta.dict.service.DictService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author Rick.Xu
 * @date 2024/9/8 21:12
 */
@Service
@RequiredArgsConstructor
public class ProductFormAdvice implements FormAdvice {

    private final DictService dictService;

    @Override
    public void beforeReturn(Form form, Long instanceId, List<FormBO.Property> propertyList, Map<String, Object> valueMap) {
        if (Objects.isNull(instanceId)) {
            // 提供默认数据
            valueMap.put("certificate", "CE, CB, RoHs, CCC");
            valueMap.put("leadTime", Arrays.asList(Arrays.asList("for first order: 50 days"), Arrays.asList("for repeat order: 35 days"), Arrays.asList("for sample: 10 days")));
            valueMap.put("packingInformation", Arrays.asList(
                    Arrays.asList("Product packing: 1pc packed into 1carton box", ""),
                    Arrays.asList("20", "384pcs"),
                    Arrays.asList("40GP", "792pcs"),
                    Arrays.asList("40HQ", "891pcs"),
                    Arrays.asList("Product size", "260*210*1050mm"),
                    Arrays.asList("Carton size", "722*357*280mm"),
                    Arrays.asList("Product net weight", "4kg"),
                    Arrays.asList("Gross weight", "6kg")));
        }

        // 获取客户信息
        List<Dict> customerDictList = dictService.getDictByType("sys_dict_customer");
        valueMap.put("customerDictList", customerDictList);

        valueMap.put("rate", getRateFromBaidu());

        //        form.getAdditionalInfo().put(FormConstants.ADDITIONAL_JS, "if (url2Object(location.search).copy === 'true') {$('#id, #code').val('');$('#code').attr('disabled', false);}");
        form.getAdditionalInfo().put(FormConstants.ADDITIONAL_JS, "if (url2Object(location.search).copy === 'true') {$('.sharp-form #id').val('');}");
    }

    @Override
    public void afterGetInstance(Form form, Long instanceId, List<FormBO.Property> propertyList, Map<String, Object> valueMap) {
        String categoryCode = (String) valueMap.get("category_code");
        if (StringUtils.isNotBlank(categoryCode)) {
            Optional<Dict> optional = dictService.getDictByTypeAndName("PRODUCT_TYPE", categoryCode);
            optional.ifPresent(dict -> {
                valueMap.put("attrFormId", dict.getRemark());
            });
        }
    }

    @Override
    public FormBO beforeRender(Map<String, ?> parameterMap, FormBO formBO) {
        if (Objects.equals(parameterMap.get("copy"), "true")) {
            // 复制 产品
            formBO.getData().put("id", null);
            formBO.getData().put("instanceId", null);
            formBO.getData().put("code", null);
            formBO.getData().put("pictures", Collections.emptyList());
            formBO.getData().put("accessoryList", Collections.emptyList());

            for (FormBO.Property property : formBO.getPropertyList()) {
                if (property.getName().equals("code")) {
                    property.setValue(null);
                } else if (property.getName().equals("pictures")) {
                    property.setValue(Collections.emptyList());
                } else if (property.getName().equals("accessoryList")) {
                    property.setValue(Collections.emptyList());
                }
            }

            return new FormBO(formBO.getForm(), null, formBO.getPropertyList(), formBO.getData(), formBO.getFormAdvice());
        }

        return FormAdvice.super.beforeRender(parameterMap, formBO);
    }

    private String getRateFromBaidu() {
//        String url = "https://iftp.chinamoney.com.cn/chinese/bkccpr/";
        String url = "https://iftp.chinamoney.com.cn/r/cms/www/chinamoney/data/fx/ccpr.json";
//        String className = ".txt-main";

        OkHttpClient client = SSLSocketClient.getUnsafeOkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();

//            Document document = Jsoup.parse(response.body().string());
//            Element element = document.selectFirst(className);
            // <div class="txt-main">7.0870</div>
//            String text = element.text();

            Map<String, ?> data = JsonUtils.toObject(response.body().string(), Map.class);
            Map<String, String> recordMap = (Map<String, String>) ((List) data.get("records")).get(0);

            return new BigDecimal(recordMap.get("price")).setScale(2, RoundingMode.HALF_UP).toPlainString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
