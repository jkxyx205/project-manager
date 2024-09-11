package com.rick.manager.module.product;

import com.rick.common.http.HttpServletResponseUtils;
import com.rick.common.http.exception.BizException;
import com.rick.excel.core.ExcelWriter;
import com.rick.excel.core.model.ExcelCell;
import com.rick.manager.module.product.entity.Product;
import com.rick.manager.module.product.model.ExportParamDTO;
import com.rick.manager.module.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @author Rick.Xu
 * @date 2024/9/11 16:52
 */
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ExportPriceController {

    private final ProductService productService;

    @GetMapping("export-price")
    public void export(HttpServletRequest request, HttpServletResponse response, ExportParamDTO exportParam) throws IOException {
        if (exportParam.getPriceType() == ExportParamDTO.PriceTypeEnum.USD && Objects.isNull(exportParam.getRate())) {
            throw new BizException("美金未税必须输入汇率");
        }

        exportToExcel(request, response, exportParam);
    }

    public void exportToExcel(HttpServletRequest request, HttpServletResponse response, ExportParamDTO exportParam) throws IOException {
        Product product = productService.findById(exportParam.getProductId()).get();
        OutputStream os = HttpServletResponseUtils.getOutputStreamAsAttachment(request, response, product.getCode() + "_" + product.getName() + ".xlsx");

        // 这样就不会改变模版文件
        final ClassPathResource classPathResource = new ClassPathResource("templates/excel/tpl.xlsx");
        byte[] bytes = IOUtils.toByteArray(classPathResource.getInputStream());

        ExcelWriter excelWriter = new ExcelWriter(new XSSFWorkbook(new ByteArrayInputStream(bytes)));
        excelWriter.writeCell(new ExcelCell(8, 3, "PO NO: "+ "test excel export"));

        // TODO

        excelWriter.toFile(os);
    }
}
