package saffchen.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import saffchen.database.ExcelConnection;
import saffchen.product.RawProduct;

import java.util.List;

class ExcelImportUtilsTest {
    @Test
    void getNotEmptyListOfProductsFromExcel() {
        ExcelConnection connection = ExcelConnection.getInstance("stock.xlsx");
        ExcelImportUtils importUtils = new ExcelImportUtils(connection);
        List<RawProduct> products = importUtils.getData();
        products.forEach(rawProduct -> System.out.println(rawProduct.showInfo()));
        Assertions.assertFalse(products.isEmpty());
    }
}
