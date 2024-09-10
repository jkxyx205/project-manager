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
 * @date 2024-09-08 20:42:04
 */
@SpringBootTest
public class ProductTest {

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
                .id(864275104153022464L)
                .code("t_product")
                .tableName("t_product")
                .tplName("modules/product/product")
                .formAdviceName("productFormAdvice")
                .additionalInfo(Params.builder(1).pv("showSaveFormBtn", true).build())
                .name("产品信息")
                .repositoryName("productDAO")
//                        .formAdviceName()
                .storageStrategy(Form.StorageStrategyEnum.CREATE_TABLE)
                .build());
        // 关联关系
        formCpnService.saveOrUpdateByConfigIds(form.getId(), cpnConfigurerList.stream().map(CpnConfigurer::getId).collect(Collectors.toList()));
        System.out.println("form id = " + form.getId());
    }

    private List<CpnConfigurer> createCpnConfigurerList() {
         Validator requiredValidator = new Required(true);

        // 文本校验器
        List<Validator> textValidatorList = Lists.newArrayListWithExpectedSize(2);
        textValidatorList.add(requiredValidator);
        textValidatorList.add(new Length(16));

        CustomizeRegex codeRegex = new CustomizeRegex("^[0-9a-zA-Z_\\/%\\-]{1,}$", "CODE只能包含数字、字母、下划线、中划线");

        List<Validator> codeRegexValidatorList = Lists.newArrayList(textValidatorList);
        codeRegexValidatorList.add(codeRegex);

        CpnConfigurer codeCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXT)
                .name("code")
                .label("型号")
                .placeholder("请输入型号")
                .validatorList(codeRegexValidatorList)
                .build();

        CpnConfigurer nameCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXT)
                .name("name")
                .label("品名")
                .placeholder("请输入品名")
                .validatorList(textValidatorList)
                .build();

        CpnConfigurer categoryCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.SELECT)
                .name("category")
                .label("分类")
                .placeholder("请选择分类")
                .datasource("sys_dict_category")
                .validatorList(Arrays.asList(requiredValidator))
                .build();

        CpnConfigurer supplierCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.SEARCH_SELECT)
                .name("supplier")
                .label("供应商")
                .placeholder("请选择供应商")
                .datasource("sys_dict_supplier")
                .validatorList(Arrays.asList(requiredValidator))
                .build();

        CpnConfigurer remarkCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXTAREA)
                .name("remark")
                .label("备注")
                .placeholder("请输入备注")
                .build();

        CpnConfigurer accessoryNameCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXT)
                .name("accessoryName")
                .label("配件")
                .placeholder("请输入配件")
                .validatorList(textValidatorList)
                .build();

        CpnConfigurer accessoryAmountCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.NUMBER_TEXT)
                .name("accessoryAmount")
                .label("单价")
                .placeholder("请输入单价")
                .build();

        CpnConfigurer accessoryCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TABLE)
                .name("accessoryList")
                .label("配件")
                .placeholder("请输入配件")
                .additionalInfo(Params.builder()
                        .pv("columns", Arrays.asList(accessoryNameCpn, accessoryAmountCpn))
                        .build())
                .build();

        CpnConfigurer pictureCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.FILE)
                .name("pictures")
                .label("图片")
                .build();

        List<CpnConfigurer> cpnConfigurerList = Lists.newArrayList(codeCpn, nameCpn, categoryCpn, supplierCpn, remarkCpn, accessoryCpn, pictureCpn);
        return cpnConfigurerList;
    }

    @Test
    public void testReport() {
        Report report = Report.builder()
                .id(864272287958904832L)
                .code("t_product")// 　建议和数据库表名保持一致
//                .tplName("tpl/list/ajax_list") // 拷贝模版页面到指定目录
                .tplName("tpl/list/list") // 没有特殊要求使用模版页面
//                .tplName("tpl/list/ajax_list") // 没有特殊要求使用模版页面
                .name("产品")
                .reportAdviceName("operatorReportAdvice")
                .additionalInfo(Params.builder(1).pv("operator-bar", true) // 显示操作按钮
//                        .pv("endpoint", "products")
                        .pv(ReportConstants.ADDITIONAL_FORM_ID, "864275104153022464")
                        .pv(ReportConstants.ADDITIONAL_FORM_ACTION, "link")
                        .build()) // 显示操作按钮
                .querySql("SELECT t_product.name AS \"name\", t_product.category_code AS \"categoryCode\", t_product.supplier_id supplierId, t_product.picture AS \"pictures\",t_product.remark AS \"remark\",t_product.instance_id AS \"instanceId\",t_product.attr_instance_id AS \"attrInstanceId\",t_product.code AS \"code\",t_product.create_by AS \"createBy\",t_product.create_time AS \"createTime\",t_product.update_by AS \"updateBy\",t_product.update_time AS \"updateTime\",t_product.is_deleted AS \"deleted\",t_product.id AS \"id\" FROM t_product WHERE name = :name AND picture = :picture AND picture = :pictures AND remark = :remark AND instance_id = :instance_id AND instance_id = :instanceId AND supplier_id = :supplierId AND category_code = :categoryCode AND code = :code AND create_by = :create_by AND create_by = :createBy AND create_time = :create_time AND create_time = :createTime AND update_by = :update_by AND update_by = :updateBy AND update_time = :update_time AND update_time = :updateTime AND is_deleted = 0 AND is_deleted = :deleted AND id = :id")
                .queryFieldList(Arrays.asList(
                        new QueryField("code", "型号"),
                        new QueryField("name", "品名"),
                        new QueryField("categoryCode", "分类", QueryField.Type.SELECT, "sys_dict_category"),
                        new QueryField("supplierId", "供应商", QueryField.Type.SEARCH_SELECT, "sys_dict_supplier")
                ))
                .reportColumnList(Arrays.asList(
                        new HiddenReportColumn("id"),
                        new ReportColumn("code", "型号"),
                        new ReportColumn("name", "品名"),
                        new ReportColumn("categoryCode", "分类", false, "sys_dict_category", Arrays.asList("dictConverter")),
                        new ReportColumn("supplierId", "供应商", false, "sys_dict_supplier", Arrays.asList("dictConverter")),
                        new ReportColumn("remark", "备注"),
                        new ReportColumn("createBy", "创建人"),
                        new ReportColumn("createTime", "创建时间", false, null, Arrays.asList("localDateTimeConverter")).setAlign(AlignEnum.CENTER).setType(ReportColumn.TypeEnum.DATETIME),
                        new ReportColumn("updateBy", "更新人"),
                        new ReportColumn("updateTime", "更新时间", false, null, Arrays.asList("localDateTimeConverter")).setAlign(AlignEnum.CENTER).setType(ReportColumn.TypeEnum.DATETIME)
                ))
                .pageable(true)
                .sidx("createTime")
                .sord(SordEnum.DESC)
                .build();

        reportService.saveOrUpdate(report);
        System.out.println("=========reportId = " + report.getId());
    }
}
