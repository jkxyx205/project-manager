package com.rick.manager.module.product.service;

import com.rick.formflow.form.cpn.core.Form;
import com.rick.formflow.form.service.FormAdvice;
import com.rick.formflow.form.service.bo.FormBO;
import com.rick.meta.dict.entity.Dict;
import com.rick.meta.dict.service.DictService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    private String getRateFromBaidu() {
        OkHttpClient client;
        client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.soltarot.com/huilv/USD_CNY/1.html")
                .build();
        try {
            Response response = client.newCall(request).execute();
            Document document = Jsoup.parse(response.body().string());
            Element element = document.selectFirst(".t");
            // <p class="t">7.092<span class="faded-digits"></span> 人民币（CNY）</p>
            String text = element.text();
            return text.substring(0, 4);
        } catch (IOException e) {
            return "";
        }
    }
}
