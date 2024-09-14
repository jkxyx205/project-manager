package com.rick.manager.module;

import com.google.common.collect.Lists;
import com.rick.db.service.support.Params;
import com.rick.formflow.form.cpn.core.CpnConfigurer;
import com.rick.formflow.form.cpn.core.CpnTypeEnum;
import com.rick.formflow.form.cpn.core.Form;
import com.rick.formflow.form.service.CpnConfigurerService;
import com.rick.formflow.form.service.FormCpnService;
import com.rick.formflow.form.service.FormService;
import com.rick.formflow.form.valid.CustomizeRegex;
import com.rick.formflow.form.valid.Length;
import com.rick.formflow.form.valid.Required;
import com.rick.formflow.form.valid.core.Validator;
import com.rick.report.core.entity.Report;
import com.rick.report.core.model.*;
import com.rick.report.core.service.ReportService;
import com.rick.report.core.support.ReportConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rick.Xu
 * @date 2024-09-14 14:27:32
 */
@SpringBootTest
public class CustomerTest {

    @Autowired
    private FormCpnService formCpnService;

    @Autowired
    private CpnConfigurerService cpnConfigurerService;

    @Autowired
    private FormService formService;

    @Autowired
    private ReportService reportService;

    @Test
    public void testForm() {
        // 设计控件
        List<CpnConfigurer> cpnConfigurerList = createCpnConfigurerList();
        cpnConfigurerService.saveOrUpdate(cpnConfigurerList);
        // 创建表
        Form form = formService.saveOrUpdate(Form.builder()
                .id(866354416721108992L)
                .code("t_customer")
                .tableName("t_customer")
//                // 1. 弹出框
                .tplName("tpl/form/form-full") // 弹出框显示需要使用 tpl/form/form-full
                .additionalInfo(Params.builder(1).pv("showSaveFormBtn", false).build())
                .name("客户信息")
                .formAdviceName("customerFormAdvice")
                .repositoryName("customerDAO")
                .storageStrategy(Form.StorageStrategyEnum.CREATE_TABLE)
                .build());
        // 关联关系
        formCpnService.saveOrUpdateByConfigIds(form.getId(), cpnConfigurerList.stream().map(CpnConfigurer::getId).collect(Collectors.toList()));
        System.out.println("form id = " + form.getId());
    }

    private List<CpnConfigurer> createCpnConfigurerList() {
        // 文本校验器
        List<Validator> textValidatorList = Lists.newArrayListWithExpectedSize(2);
        textValidatorList.add(new Required(true));
        textValidatorList.add(new Length(16));

        CustomizeRegex codeRegex = new CustomizeRegex("^[0-9a-zA-Z_\\/%\\-]{1,}$", "CODE只能包含数字、字母、下划线、中划线");

        List<Validator> codeRegexValidatorList = Lists.newArrayList(textValidatorList);
        codeRegexValidatorList.add(codeRegex);

        CpnConfigurer codeCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXT)
                .name("code")
                .label("编号")
                .placeholder("请输入编号")
                .validatorList(codeRegexValidatorList)
                .build();

        CpnConfigurer nameCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXT)
                .name("name")
                .label("客户")
                .placeholder("请输入供应商")
                .validatorList(textValidatorList)
                .build();

        CpnConfigurer addressCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXT)
                .name("address")
                .label("地址")
                .placeholder("请输入地址")
                .build();

        CpnConfigurer contactNameCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXT)
                .name("contactName")
                .label("联系人")
                .placeholder("请输入联系人")
                .validatorList(textValidatorList)
                .build();

        CpnConfigurer whatsappCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXT)
                .name("whatsApp")
                .label("Whats App")
                .placeholder("请输入Whats App")
                .validatorList(textValidatorList)
                .build();

        CpnConfigurer remarkCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXTAREA)
                .name("remark")
                .label("备注")
                .placeholder("请输入备注")
                .build();

        List<CpnConfigurer> cpnConfigurerList = Lists.newArrayList(codeCpn, nameCpn, addressCpn, contactNameCpn, whatsappCpn, remarkCpn);
        return cpnConfigurerList;
    }
    @Test
    public void testReport() {
        Report report = Report.builder()
                .code("t_customer")// 　建议和数据库表名保持一致
//                .tplName("tpl/list/ajax_list") // 拷贝模版页面到指定目录
                .tplName("tpl/list/list") // 没有特殊要求使用模版页面
//                .tplName("tpl/list/ajax_list") // 没有特殊要求使用模版页面
                .name("客户")
                .reportAdviceName("operatorReportAdvice")
                .additionalInfo(Params.builder(1).pv("operator-bar", true) // 显示操作按钮
                        .pv(ReportConstants.ADDITIONAL_FORM_ID, "866354416721108992")
                        .build()) // 显示操作按钮
                .querySql("SELECT t_customer.name AS \"name\",t_customer.contact_name AS \"contactName\",t_customer.whats_app AS \"whatsApp\",t_customer.remark AS \"remark\",t_customer.code AS \"code\",t_customer.create_by AS \"createBy\",t_customer.create_time AS \"createTime\",t_customer.update_by AS \"updateBy\",t_customer.update_time AS \"updateTime\",t_customer.is_deleted AS \"deleted\",t_customer.id AS \"id\" FROM t_customer WHERE name LIKE :name AND contact_name LIKE :contact_name AND contact_name LIKE :contactName AND whatsapp = :whatsapp AND whatsapp = :whatsApp AND remark = :remark AND code LIKE :code AND create_by = :create_by AND create_by = :createBy AND create_time = :create_time AND create_time = :createTime AND update_by = :update_by AND update_by = :updateBy AND update_time = :update_time AND update_time = :updateTime AND is_deleted = 0 AND is_deleted = :deleted AND id = :id")
                .queryFieldList(Arrays.asList(
                        new QueryField("code", "编号"),
                        new QueryField("id", "客户", QueryField.Type.SEARCH_SELECT, "sys_dict_customer"),
                        new QueryField("contact_name", "联系人")
//                        new QueryField("whatsApp", "whatsApp"),
//                        new QueryField("remark", "备注"),
//                        new QueryField("create_by", "创建人"),
//                        new QueryField("create_time", "创建时间", QueryField.Type.DATE_RANGE),
//                        new QueryField("update_by", "更新人"),
//                        new QueryField("update_time", "更新时间", QueryField.Type.DATE_RANGE)
                ))
                .reportColumnList(Arrays.asList(
                        new HiddenReportColumn("id"),
                        new ReportColumn("code", "编号"),
                        new ReportColumn("name", "客户"),
                        new ReportColumn("contactName", "联系人"),
                        new ReportColumn("whatsApp", "联系电话"),
                        new ReportColumn("remark", "备注"),
                        new ReportColumn("createBy", "创建人"),
                        new ReportColumn("createTime", "创建时间", false, null, Arrays.asList("localDateTimeConverter")).setColumnWidth(120).setAlign(AlignEnum.CENTER).setType(ReportColumn.TypeEnum.DATETIME),
                        new ReportColumn("updateBy", "更新人"),
                        new ReportColumn("updateTime", "更新时间", false, null, Arrays.asList("localDateTimeConverter")).setColumnWidth(120).setAlign(AlignEnum.CENTER).setType(ReportColumn.TypeEnum.DATETIME)
                ))
                .pageable(true)
                .sidx("createTime")
                .sord(SordEnum.DESC)
                .build();

        reportService.saveOrUpdate(report);
        System.out.println("=========reportId = " + report.getId());
    }
}
