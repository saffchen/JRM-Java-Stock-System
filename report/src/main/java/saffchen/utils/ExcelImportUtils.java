package saffchen.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import saffchen.database.ExcelConnection;
import saffchen.product.RawProduct;
import saffchen.product.ReflectProductUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelImportUtils implements ImportUtils {
    private ExcelConnection excelConnection;
    private final int COMLUMN_COUNT = 7;

    public ExcelImportUtils(ExcelConnection excelConnection) {
        this.excelConnection = excelConnection;
    }

    @Override
    public List<RawProduct> getData() throws Exception {
        XSSFWorkbook myExcelBook = null;
        List<RawProduct> products = new ArrayList<>();
        try {
            myExcelBook = new XSSFWorkbook(new FileInputStream(excelConnection.getFilePath()));

            XSSFSheet myExcelSheet = myExcelBook.getSheet("stock_import_csv");
            XSSFRow row = null;

            ReflectProductUtils reflectProductUtils = new ReflectProductUtils();
            List<String> headersFromClass = reflectProductUtils.getFieldsFromClass(RawProduct.class);

            for (int i = 1; i < myExcelSheet.getLastRowNum(); i++) {
                row = myExcelSheet.getRow(i);
                RawProduct rawProduct = new RawProduct();
                String strValueInCell = null;
                for (int j = 0; j < COMLUMN_COUNT; j++) {
                    if (row.getCell(j).getCellType() == XSSFCell.CELL_TYPE_STRING)
                        strValueInCell = row.getCell(j).getStringCellValue();
                    if (row.getCell(j).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                        strValueInCell = String.valueOf(row.getCell(j).getNumericCellValue());
                    }
                    reflectProductUtils.invokeSetter(rawProduct,
                            headersFromClass.get(j),
                            (Object) strValueInCell);
                }
                products.add(rawProduct);
            }
            myExcelBook.close();

        } catch (IOException e) {
            System.out.println("Error: Can't read the xls-file");
        } catch (Exception e) {
            try {
                myExcelBook.close();
            } catch (Exception a) {
                System.out.println("Error: Can't close the file");
            }
        }
        return products;
    }


}
