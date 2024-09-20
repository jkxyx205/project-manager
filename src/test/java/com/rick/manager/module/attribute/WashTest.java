package com.rick.manager.module.attribute;

import com.google.common.collect.Lists;
import com.rick.db.service.support.Params;
import com.rick.formflow.form.cpn.core.CpnConfigurer;
import com.rick.formflow.form.cpn.core.CpnTypeEnum;
import com.rick.formflow.form.cpn.core.Form;
import com.rick.formflow.form.service.FormConstants;
import com.rick.formflow.form.service.FormService;
import com.rick.formflow.form.valid.CustomizeRegex;
import com.rick.formflow.form.valid.Length;
import com.rick.formflow.form.valid.Required;
import com.rick.formflow.form.valid.core.Validator;
import com.rick.manager.core.FormSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rick.Xu
 * @date 2024-09-08 19:05:08
 */
@SpringBootTest
public class WashTest {

    @Autowired
    private FormService formService;

    @Autowired
    private FormSupport formSupport;

    @Test
    public void testForm() {
        // 创建表
        Form form = formService.saveOrUpdate(Form.builder()
                .id(864287608526680064L)
                .code("wash")
                .tableName("wash")
                .tplName("tpl/form/form") // 弹出框显示需要使用 tpl/form/form-full
                .formAdviceName("washFormAdvice")
                .additionalInfo(Params.builder(1)
                        .pv(FormConstants.ADDITIONAL_SHOW_SAVE_FORM_BTN, false)
                        .pv(FormConstants.ADDITIONAL_LABEL_COL, 0) // 不显示标签
                        .build())
                .name("洗地机参数")
                .storageStrategy(Form.StorageStrategyEnum.INNER_TABLE)
                .build());

        // 设计控件
        List<CpnConfigurer> cpnConfigurerList = createCpnConfigurerList();

        formSupport.bind(form.getId(), cpnConfigurerList);

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

//        CpnConfigurer dlCpn = CpnConfigurer.builder()
//                .cpnType(CpnTypeEnum.TEXT)
//                .name("dl")
//                .label("电流")
//                .placeholder("请输入电流")
////                .validatorList(textValidatorList)
//                .build();

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

        CpnConfigurer remarkValueCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TEXT)
                .name("paramRemark")
                .label("备注")
                .placeholder("请输入备注")
                .build();

        CpnConfigurer paramCpn = CpnConfigurer.builder()
                .cpnType(CpnTypeEnum.TABLE)
                .name("param")
                .label("其他参数")
                .placeholder("请输入参数")
                .additionalInfo(Params.builder()
                        .pv("columns", Arrays.asList(paramNameCpn, paramValueCpn, remarkValueCpn))
                        .build())
                .build();

        List<CpnConfigurer> cpnConfigurerList = Lists.newArrayList(paramCpn); /*dlCpn*/
        return cpnConfigurerList;
    }

}
