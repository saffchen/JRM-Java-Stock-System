package saffchen.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;
import saffchen.database.ExcelConnection;
import saffchen.product.RawProduct;
import saffchen.product.ReflectProductUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExcelImportUtils implements ImportUtils {
    private final ExcelConnection excelConnection;

    public ExcelImportUtils(ExcelConnection excelConnection) {
        this.excelConnection = excelConnection;
    }

    @Override
    public List<RawProduct> getData() {
        List<RawProduct> products = new ArrayList<>();
        URL fileUrl = getClass().getClassLoader().getResource(excelConnection.getFilePath());
        try (FileInputStream fis = new FileInputStream(fileUrl.getPath());
             XSSFWorkbook myExcelBook = new XSSFWorkbook(fis)) {
            XSSFSheet myExcelSheet = myExcelBook.getSheet("stock_import_csv");
            collectRawProductsFromExcelSheet(products, myExcelSheet);
        } catch (IOException e) {
            System.out.println("Error: Can't read the xls-file");
        }
        return products;
    }

    private static void collectRawProductsFromExcelSheet(List<RawProduct> products, XSSFSheet excelSheet) {
        ReflectProductUtils reflectProductUtils = new ReflectProductUtils();
        List<String> headersFromClass = reflectProductUtils.getFieldsFromClass(RawProduct.class);
        XSSFRow row;
        for (int i = 1; i <= excelSheet.getLastRowNum(); i++) {
            row = excelSheet.getRow(i);
            RawProduct rawProduct = new RawProduct();
            String strValueInCell = "";
            for (int j = 0; j < headersFromClass.size(); j++) {
                if (row.getCell(j).getCellType() == XSSFCell.CELL_TYPE_STRING)
                    strValueInCell = row.getCell(j).getStringCellValue();
                if (row.getCell(j).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    strValueInCell = String.valueOf(row.getCell(j).getNumericCellValue());
                }
                reflectProductUtils.invokeSetter(rawProduct,
                        headersFromClass.get(j),
                        strValueInCell);
            }
            products.add(rawProduct);
        }
    }

}
