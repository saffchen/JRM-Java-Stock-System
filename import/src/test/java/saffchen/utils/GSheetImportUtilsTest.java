package saffchen.utils;

import com.google.api.services.sheets.v4.Sheets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import saffchen.database.GSheetConnection;
import saffchen.product.RawProduct;

import java.io.IOException;
import java.util.List;

public class GSheetImportUtilsTest {
    @Test
    void getNotEmptyListOfProductsFromGoogleSheet() throws IOException {
        Sheets sheets = GSheetConnection.getSheetsService();
        GSheetImportUtils importUtils = new GSheetImportUtils(sheets);
        List<RawProduct> products = importUtils.getData();
        products.forEach(rawProduct -> System.out.println(rawProduct.showInfo()));
        Assertions.assertFalse(products.isEmpty());
    }
}
