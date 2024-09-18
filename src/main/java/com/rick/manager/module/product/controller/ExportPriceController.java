package com.rick.manager.module.product.controller;

import com.rick.common.http.HttpServletResponseUtils;
import com.rick.common.util.BigDecimalUtils;
import com.rick.common.util.Time2StringUtils;
import com.rick.excel.core.ExcelWriter;
import com.rick.excel.core.model.ExcelCell;
import com.rick.formflow.form.service.FormService;
import com.rick.formflow.form.service.bo.FormBO;
import com.rick.manager.module.customer.entity.Customer;
import com.rick.manager.module.customer.service.CustomerService;
import com.rick.manager.module.product.constant.PriceConstants;
import com.rick.manager.module.product.entity.Product;
import com.rick.manager.module.product.model.ExportParamDTO;
import com.rick.manager.module.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Rick.Xu
 * @date 2024/9/11 16:52
 */
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ExportPriceController {

    private final ProductService productService;

    private final CustomerService customerService;

    private final FormService formService;

    @GetMapping("export-price")
    public void export(HttpServletRequest request, HttpServletResponse response, ExportParamDTO exportParam) throws IOException {
//        if (exportParam.getPriceType() == ExportParamDTO.PriceTypeEnum.USD && Objects.isNull(exportParam.getRate())) {
//            throw new BizException("美金未税必须输入汇率");
//        }

        exportToExcel(request, response, exportParam);
    }

    public void exportToExcel(HttpServletRequest request, HttpServletResponse response, ExportParamDTO exportParam) throws IOException {
        Customer customer = customerService.findById(exportParam.getCustomerId()).get();
        Product product = productService.findById(exportParam.getProductId()).get();
        FormBO formBO = formService.getFormBOByIdAndInstanceId(864287608526680064L, product.getAttrInstanceId());

        OutputStream os = HttpServletResponseUtils.getOutputStreamAsAttachment(request, response, "COMPASS-Quotation-"+product.getCode()+" "+product.getName()+"-"+ Time2StringUtils.format(LocalDate.now()) +"" + ".xlsx");

        ExcelWriter excelWriter;
        Map<String, String> usdPriceTemplate = product.getUsdPriceTemplate();
        Map<String, String> rmbPriceTemplate = product.getRmbPriceTemplate();

        if (exportParam.getPriceType() == ExportParamDTO.PriceTypeEnum.USD && MapUtils.isNotEmpty(usdPriceTemplate)) {
            excelWriter = new ExcelWriter(new XSSFWorkbook(new URL(product.getUsdPriceTemplate().get("url")).openStream()));
        } else if (exportParam.getPriceType() == ExportParamDTO.PriceTypeEnum.RMB && MapUtils.isNotEmpty(rmbPriceTemplate)) {
            excelWriter = new ExcelWriter(new XSSFWorkbook(new URL(product.getRmbPriceTemplate().get("url")).openStream()));
        } else {
            // throw new BizException("请先上传价格模版文件");
            String accessory = CollectionUtils.isNotEmpty(product.getAccessoryList()) ? "-accessory-" + product.getAccessoryList().size()  : "";

            final ClassPathResource classPathResource = new ClassPathResource("templates/excel/template"+accessory+"-"+exportParam.getPriceType().name()+".xlsx");
            byte[] bytes = IOUtils.toByteArray(classPathResource.getInputStream());
            excelWriter = new ExcelWriter(new XSSFWorkbook(new ByteArrayInputStream(bytes)));
        }

        LocalDate now = LocalDate.now();
        Font boldFont = boldFont(excelWriter.getBook());
        excelWriter.getBook().setSheetName(0, product.getCode());

        int customerInfoStartIndex = 5;
        if (StringUtils.isNotBlank(customer.getName())) {
            excelWriter.writeCell(new ExcelCell(1, customerInfoStartIndex, "Company: " + customer.getName()), (ecell, cell) -> cell.getRichStringCellValue().applyFont(0, 9, boldFont));
            customerInfoStartIndex++;
        }

        if (StringUtils.isNotBlank(customer.getAddress())) {
            excelWriter.writeCell(new ExcelCell(1, customerInfoStartIndex, "Address: " + customer.getAddress()), (ecell, cell) -> cell.getRichStringCellValue().applyFont(0, 9, boldFont));
            customerInfoStartIndex++;
        }

        if (StringUtils.isNotBlank(customer.getContactName())) {
            excelWriter.writeCell(new ExcelCell(1, customerInfoStartIndex, "Contact: " + customer.getContactName()), (ecell, cell) -> cell.getRichStringCellValue().applyFont(0, 9, boldFont));
            customerInfoStartIndex++;
        }

        if (StringUtils.isNotBlank(customer.getWhatsApp())) {
            excelWriter.writeCell(new ExcelCell(1, customerInfoStartIndex, "Whats App: " + customer.getWhatsApp()), (ecell, cell) -> cell.getRichStringCellValue().applyFont(0, 9, boldFont));
        }

        BigDecimal tax = product.getPriceType() == ExportParamDTO.PriceTypeEnum.RMB ? PriceConstants.rate : BigDecimal.ONE;
        BigDecimal actualProductPrice = product.getPriceType() == ExportParamDTO.PriceTypeEnum.USD ? product.getUsdPrice() : product.getRmbPrice();

        excelWriter.writeCell(new ExcelCell(5, 5, "CPS" + Time2StringUtils.format(now).replace("-", "")));
        excelWriter.writeCell(new ExcelCell(5, 6, now.format(DateTimeFormatter.ofPattern("MMM d, yyyy"))));
//        excelWriter.writeCell(new ExcelCell(5, 7, exportParam.getIncoterm()));

        StringBuilder paramBuilder = new StringBuilder();
        Map<String, Object> propertyData = formBO.getPropertyData();

        // 产品
        BigDecimal productPrice = getCalculatePrice(exportParam, product, actualProductPrice, tax);

        BigDecimal totalPrice = BigDecimal.ZERO;
        totalPrice = totalPrice.add(productPrice);

        int s1 = 0;
        int s2 = (product.getCode() + " " + product.getName()).length();

        List<List<Integer>> boldIndex = new ArrayList<>();
        int start = (product.getCode() + " " + product.getName() + "\n" + product.getDescription() + "\n\n").length() - 1;
        int len = start;

        if (CollectionUtils.isNotEmpty((List<List<String>>)propertyData.get("param"))) {
            for (List<String> param : (List<List<String>>)propertyData.get("param")) {
                String paramName= param.get(0);
                String paramValue= param.get(1);
                paramBuilder.append("* ");
                if (StringUtils.isNotBlank(paramName)) {
                    paramBuilder.append(paramName).append(": ");
                    int end = start + paramName.length() + 5;
                    boldIndex.add(Arrays.asList(start, end));
                } else { // 只有 paramValue
                    int end = start + paramValue.length() + 3;
                    boldIndex.add(Arrays.asList(start, end));
                }

                paramBuilder.append(paramValue).append("\n");
                start = len + paramBuilder.length();
            }
        }

        excelWriter.writeCell(new ExcelCell(3, 11, product.getCode() + " " + product.getName() + "\n" + product.getDescription() + "\n\n" + paramBuilder),
                ((ecell, cell) -> {
                    cell.getRichStringCellValue().applyFont(s1, s2, boldFont);
                    if (CollectionUtils.isNotEmpty(boldIndex)) {
                        for (List<Integer> index : boldIndex) {
                            cell.getRichStringCellValue().applyFont(index.get(0), index.get(1), boldFont);
                        }
                    }
                }));

        excelWriter.writeCell(new ExcelCell(4, 11, exportParam.getQuantity()));
        excelWriter.writeCell(new ExcelCell(5, 11, productPrice));
        excelWriter.writeCell(new ExcelCell(6, 11, new BigDecimal(BigDecimalUtils.formatBigDecimalValue(productPrice.multiply(BigDecimal.valueOf(exportParam.getQuantity()))))));
        if (StringUtils.isNotBlank(product.getMoq())) {
            excelWriter.writeCell(new ExcelCell(7, 8, exportParam.getQuantity() < Integer.parseInt(StringUtils.substringBefore(product.getMoq(), "p")) ? "数量少于MOQ。单价可能发生变化。请与供应商确认价格。" : "数量大于MOQ。请与供应商讲价，获取更优惠的价格！"));
        }

        // 计算过程
        excelWriter.writeCell(new ExcelCell(7, 9, product.getPriceType() == ExportParamDTO.PriceTypeEnum.USD ? "$" : "¥"));
        excelWriter.writeCell(new ExcelCell(7, 11, Objects.nonNull(product.getUsdPrice()) ? product.getUsdPrice() : product.getRmbPrice()));
        excelWriter.writeCell(new ExcelCell(8, 11, tax));
        excelWriter.writeCell(new ExcelCell(9, 11, exportParam.getStep()));
        excelWriter.writeCell(new ExcelCell(10, 11, exportParam.getRate()));
        excelWriter.writeCell(new ExcelCell(11, 11, productPrice));

        if (CollectionUtils.isNotEmpty(product.getPictures())) {
            // 插入图片
            excelWriter.createPicture(ExcelWriter.PathType.URL,
                    product.getPictures().get(0).get("url"),
                    product.getPictures().get(0).get("extension"),
                    1, 10, 2, 11);
        }

        // 配件
        final int accessoryStart = 14;

        int elseStartIndex;
        if (CollectionUtils.isNotEmpty(product.getAccessoryList())) {
            List<Object[]> dataList = new ArrayList<>();
            int index = 2;
            for (List<String> param : product.getAccessoryList()) {
                String name= param.get(0);
                BigDecimal accessoryPrice = null;
                BigDecimal accessoryTotalPrice = null;

                if (StringUtils.isNotBlank(param.get(1))) {
                    accessoryPrice = new BigDecimal(param.get(1)).divide(new BigDecimal(exportParam.getStep()), 4, RoundingMode.HALF_UP).divide(tax, 2, RoundingMode.HALF_UP);
                    accessoryPrice = getCalculatePrice(exportParam, product, accessoryPrice, tax);

                    accessoryTotalPrice = new BigDecimal(BigDecimalUtils.formatBigDecimalValue(accessoryPrice.multiply(BigDecimal.valueOf(exportParam.getQuantity()))));
                    totalPrice = totalPrice.add(accessoryPrice);
                }

                dataList.add(new Object[] { index++, null, name, exportParam.getQuantity(),
                        BigDecimalUtils.formatBigDecimalValue(accessoryPrice), accessoryTotalPrice,
                        BigDecimalUtils.formatBigDecimalValue(StringUtils.isBlank(param.get(1)) ? null : new BigDecimal(param.get(1))), tax, BigDecimalUtils.formatBigDecimalValue(new BigDecimal(exportParam.getStep())), BigDecimalUtils.formatBigDecimalValue(exportParam.getRate()), accessoryPrice});
            }

            excelWriter.insertAndWriteRowWithAfterRowStyle2(accessoryStart, dataList, (ecell, cell) -> {
                if (ecell.getX() == 11) {
                    cell.setCellFormula("IF(AND(G9=\"¥\",K9=\"$\"),G11/I11/J11/H11,IF(AND(G9=\"$\",K9=\"¥\"),G11/I11*J11,G11/I11/H11))".replace("11", ecell.getY() + ""));
                }
            });

            // 插入图片
            int index2 = 0;
            for (List<String> param : product.getAccessoryList()) {
                if (StringUtils.isNotBlank(param.get(2))) {
                    excelWriter.createPicture(ExcelWriter.PathType.URL,
                            param.get(2),
                            StringUtils.substringAfterLast(param.get(2), "."),
                            1, accessoryStart + index2 - 1, 2, accessoryStart + index2);
                }
                index2++;
            }

            // set total amount
            excelWriter.writeCell(new ExcelCell(5, accessoryStart + index2 + 1, totalPrice));
            excelWriter.writeCell(new ExcelCell(6, accessoryStart + index2 + 1, totalPrice.multiply(BigDecimal.valueOf(exportParam.getQuantity()))));
            excelWriter.removeRow(accessoryStart + index - 2); // 删除空行
            elseStartIndex = accessoryStart + index;
        } else {
            // set total amount
            excelWriter.writeCell(new ExcelCell(5, 15, totalPrice));
            excelWriter.writeCell(new ExcelCell(6, 15, totalPrice.multiply(BigDecimal.valueOf(exportParam.getQuantity()))));
            // 无配件删除多余的行
            excelWriter.removeRows(accessoryStart - 1, 2);
            elseStartIndex = accessoryStart + 1;
        }

        // 产品卖点
        if (CollectionUtils.isNotEmpty(product.getSellingPoint())) {
            int index = 0;
            for (List<String> value : product.getSellingPoint()) {
                excelWriter.writeCell(new ExcelCell(1, elseStartIndex + index, value.get(0)));
                index++;
            }
        }

        // 认证
        excelWriter.writeCell(new ExcelCell(3, elseStartIndex , product.getCertificate()));
        excelWriter.writeCell(new ExcelCell(3, elseStartIndex + 2 , product.getMoq()));

        // 交货时间
        if (CollectionUtils.isNotEmpty(product.getLeadTime())) {
            int index = 0;
            for (List<String> value : product.getLeadTime()) {
                excelWriter.writeCell(new ExcelCell(3, elseStartIndex + 4 + index, value.get(0)));
                index++;
            }
        }

        // 包装信息
        if (CollectionUtils.isNotEmpty(product.getPackingInformation())) {
            int index = 0;
            for (List<String> value : product.getPackingInformation()) {
                excelWriter.writeCell(new ExcelCell(4, elseStartIndex + index, value.get(0)));
                excelWriter.writeCell(new ExcelCell(5, elseStartIndex + index, value.get(1)));
                index++;
            }
        }

        excelWriter.toFile(os);
    }

    private BigDecimal getCalculatePrice(ExportParamDTO exportParam, Product product, BigDecimal actualProductPrice, BigDecimal tax) {
        BigDecimal multiply = new BigDecimal(exportParam.getStep()).multiply(tax);

        if (product.getPriceType() == ExportParamDTO.PriceTypeEnum.RMB && exportParam.getPriceType() == ExportParamDTO.PriceTypeEnum.USD) {
            multiply = multiply.multiply(exportParam.getRate());
        } else if (product.getPriceType() == ExportParamDTO.PriceTypeEnum.USD && exportParam.getPriceType() == ExportParamDTO.PriceTypeEnum.RMB) {
            actualProductPrice = actualProductPrice.multiply(exportParam.getRate());
        } else {
            exportParam.setRate(null);
        }
        return actualProductPrice.divide(multiply, 2, RoundingMode.HALF_UP);
    }

    private CellStyle boldFontStyle(Workbook workbook) {
        CellStyle boldStyle = workbook.createCellStyle();
        boldStyle.setFont(boldFont(workbook));
        return boldStyle;
    }

    private Font boldFont(Workbook workbook) {
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        return boldFont;
    }

}
