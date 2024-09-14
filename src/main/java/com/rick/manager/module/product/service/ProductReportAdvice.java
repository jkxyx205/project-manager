package com.rick.manager.module.product.service;

import com.rick.common.util.JsonUtils;
import com.rick.db.service.SharpService;
import com.rick.manager.module.common.service.OperatorReportAdvice;
import com.rick.report.core.entity.Report;
import com.rick.report.core.support.ReportConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Rick.Xu
 * @date 2024/9/14 12:14
 */
@Component
public class ProductReportAdvice extends OperatorReportAdvice {

    public ProductReportAdvice(SharpService sharpService) {
        super(sharpService);
    }

    @Override
    public void beforeSetRow(Report report, List<Map<String, Object>> rows) {
        super.beforeSetRow(report, rows);

        // 产品图片
        for (Map<String, Object> row : rows) {
            if (StringUtils.isNotBlank((CharSequence) row.get("pictures"))) {
                List<Map> pictures = JsonUtils.toList((String) row.get("pictures"), Map.class);
                if (CollectionUtils.isNotEmpty(pictures)) {
                    Map pictureMap = pictures.get(0);
                    row.put("avatar", pictureMap.get("url"));
                } else {
                    row.put("avatar","/img/default_avatar.png");
                }

                row.put("pictures", pictures);
            } else {
                row.put("pictures", Collections.emptyList());
            }
        }
    }

    @Override
    public void init(Report report) {
        report.getAdditionalInfo().put(ReportConstants.ADDITIONAL_LINK, "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/@fancyapps/ui@5.0/dist/fancybox/fancybox.css\"");
        report.getAdditionalInfo().put(ReportConstants.ADDITIONAL_SCRIPT,"<script src=\"https://cdn.jsdelivr.net/npm/@fancyapps/ui@5.0/dist/fancybox/fancybox.umd.js\"></script>");

        report.getAdditionalInfo().put(ReportConstants.ADDITIONAL_JS, "$('table tr').find('td:eq(3)').each(function(index){\n" +
                "    $(this).html('<a class=\"picture\" data-fancybox=\"picture-'+index+'\" data-src=\"'+$(this).text().trim()+'\"><img height=\"20\" src=\"'+$(this).text().trim()+'?x-oss-process=image/resize,h_20\"/></a>')\n" +
                "    \n" +
                "})\n" +
                "$(function() {Fancybox.bind('a.picture', {})})");
    }
}
