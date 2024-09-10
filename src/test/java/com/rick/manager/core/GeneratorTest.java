package com.rick.manager.core;

import com.rick.db.service.support.Params;
import com.rick.generator.Generator;
import com.rick.generator.control.FormLayoutEnum;
import com.rick.generator.control.RenderTypeEnum;
import com.rick.manager.module.product.entity.Product;
import com.rick.manager.module.supplier.entity.Supplier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author Rick.Xu
 * @date 2024/8/24 15:55
 */
@SpringBootTest
public class GeneratorTest {

    @Autowired
    private Generator generator;

    @Test
    public void testGeneratorSupplier() throws IOException {
        generator.execute(Supplier.class,
                "/Users/rick/Space/Workspace/product-manager/src/main/java/com/rick/manager/module/supplier",
                Params.builder()
//                        .pv(Generator.GENERATOR_CODE, true) // 是否创建 code template；如果没有指定，则没有就创建，否则不覆盖
                        .pv(Generator.PROJECT, "manager")
//                        .pv(Generator.FORM_PAGE, "demos/student/edit-thymeleaf") // 编辑页面路径

                        .pv(Generator.REPORT, true) // 是否创建 report
                        .pv(Generator.REPORT_TEST_PATH, "/Users/rick/Space/Workspace/product-manager/src/test/java/com/rick/manager/module")
                        .pv(Generator.REPORT_TEST_PACKAGE, "com.rick.manager.module")

                        // 表名默认 control.html
//                        .pv(Generator.CONTROL_LABEL, false)
//                        .pv(Generator.CONTROL_PATH, "/Users/rick/Space/Workspace/sharp-project/sharp-admin/src/main/resources/templates/demos/student")
                        .pv(Generator.FORM_LAYOUT, FormLayoutEnum.HORIZONTAL) // 默认就是 HORIZONTAL
                        .pv(Generator.CONTROL_RENDER_TYPE, RenderTypeEnum.THYMELEAF)

                        .build());
    }

    @Test
    public void testGeneratorProduct() throws IOException {
        generator.execute(Product.class,
                "/Users/rick/Space/Workspace/product-manager/src/main/java/com/rick/manager/module/product",
                Params.builder()
                        .pv(Generator.CREATE_TABLE, false)
//                        .pv(Generator.GENERATOR_CODE, false) // 是否创建 code template；如果没有指定，则没有就创建，否则不覆盖
                        .pv(Generator.PROJECT, "manager") // 项目的 package
//                        .pv(Generator.FORM_PAGE, "demos/student/edit-thymeleaf") // 编辑页面路径

                        .pv(Generator.REPORT, false) // 是否创建 report
//                        .pv(Generator.REPORT_TEST_PATH, "/Users/rick/Space/Workspace/product-manager/src/test/java/com/rick/manager/module")
//                        .pv(Generator.REPORT_TEST_PACKAGE, "com.rick.manager.module")

                        // 表名默认 control.html
                        .pv(Generator.CONTROL_LABEL, true)
                        .pv(Generator.CONTROL_PATH, "/Users/rick/Space/Workspace/product-manager/src/main/resources/templates/modules/product")
                        .pv(Generator.FORM_LAYOUT, FormLayoutEnum.HORIZONTAL) // 默认就是 HORIZONTAL
                        .pv(Generator.CONTROL_RENDER_TYPE, RenderTypeEnum.THYMELEAF)

                        .build());


    }

}
