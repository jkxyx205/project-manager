package com.rick.manager.module.attribute;

import com.google.common.collect.Lists;
import com.rick.db.service.support.Params;
import com.rick.formflow.form.cpn.core.CpnConfigurer;
import com.rick.formflow.form.cpn.core.CpnTypeEnum;
import com.rick.formflow.form.cpn.core.Form;
import com.rick.formflow.form.service.CpnConfigurerService;
import com.rick.formflow.form.service.FormConstants;
import com.rick.formflow.form.service.FormCpnService;
import com.rick.formflow.form.service.FormService;
import com.rick.formflow.form.valid.CustomizeRegex;
import com.rick.formflow.form.valid.Length;
import com.rick.formflow.form.valid.Required;
import com.rick.formflow.form.valid.core.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rick.Xu
 * @date 2024-09-08 19:05:08
 */
@SpringBootTest
public class WashTest {

    @Autowired
    private FormCpnService formCpnService;

    @Autowired
    private CpnConfigurerService cpnConfigurerService;

    @Autowired
    private FormService formService;

    @Test
    public void testForm() {
        // 设计控件
        List<CpnConfigurer> cpnConfigurerList = createCpnConfigurerList();
        cpnConfigurerService.saveOrUpdate(cpnConfigurerList);
        // 创建表
        Form form = formService.saveOrUpdate(Form.builder()
                .id(864287608526680064L)
                .code("wash")
                .tableName("wash")
                .tplName("tpl/form/form") // 弹出框显示需要使用 tpl/form/form-full
                .additionalInfo(Params.builder(1)
                        .pv(FormConstants.ADDITIONAL_SHOW_SAVE_FORM_BTN, false)
                        .pv(FormConstants.ADDITIONAL_LABEL_COL, "1")
                        .build())
                .name("洗地机参数")
                .storageStrategy(Form.StorageStrategyEnum.INNER_TABLE)
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

        CpnConfigurer dlCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXT)
                .name("dl")
                .label("电流")
                .placeholder("请输入电流")
//                .validatorList(textValidatorList)
                .build();

        CpnConfigurer paramNameCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXT)
                .name("paramName")
                .label("参数名")
                .placeholder("请输入参数名")
                .validatorList(textValidatorList)
                .build();

        CpnConfigurer paramValueCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.NUMBER_TEXT)
                .name("paramValue")
                .label("参数值")
                .placeholder("请输入参数值")
                .validatorList(textValidatorList)
                .build();

        CpnConfigurer paramCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TABLE)
                .name("param")
                .label("其他参数")
                .placeholder("请输入参数")
                .additionalInfo(Params.builder()
                        .pv("columns", Arrays.asList(paramNameCpn, paramValueCpn))
                        .build())
                .build();

        List<CpnConfigurer> cpnConfigurerList = Lists.newArrayList(dlCpn, paramCpn);
        return cpnConfigurerList;
    }

}
