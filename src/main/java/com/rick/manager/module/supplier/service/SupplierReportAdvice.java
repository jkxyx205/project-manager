package com.rick.manager.module.supplier.service;

import com.rick.db.service.SharpService;
import com.rick.manager.module.common.service.OperatorReportAdvice;
import com.rick.report.core.entity.Report;
import com.rick.report.core.support.ReportConstants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Rick.Xu
 * @date 2024/9/17 07:08
 */
@Component
public class SupplierReportAdvice extends OperatorReportAdvice {

    public SupplierReportAdvice(SharpService sharpService) {
        super(sharpService);
    }

    @Override
    public void beforeSetRow(Report report, List<Map<String, Object>> rows) {
//        for (Map<String, Object> row : rows) {
//            if (StringUtils.isNotBlank((String) row.get("attachment"))) {
//                List<Map> list = JsonUtils.toList((String)row.get("attachment"), Map.class);
//                row.put("attachment", list);
//            }
//        }
    }

    @Override
    public void init(Report report) {
        report.getAdditionalInfo().put(ReportConstants.ADDITIONAL_JS,
                // language=JavaScript
                "$('table tr').find('td:eq(5)').each(function () {\n" +
                        "    if ($(this).text().trim()) {\n" +
                        "        let attachments = JSON.parse($(this).text().trim())\n" +
                        "        let attachmentsHtml = []\n" +
                        "        for (let attachment of attachments) {\n" +
                        "            attachmentsHtml.push('<a href=\"' + attachment.url + '\" target=\"_blank\">' + attachment.fullName + '</a>')\n" +
                        "        }\n" +
                        "        $(this).html(attachmentsHtml.join(', '))\n" +
                        "    }\n" +
                        "}" +
                        ")");
    }
}
