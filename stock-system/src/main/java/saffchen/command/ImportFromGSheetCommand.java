package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.database.GSheetConnection;
import saffchen.product.Product;
import saffchen.utils.FileStorageUtils;
import saffchen.utils.GSheetStorageUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ImportFromGSheetCommand implements Command{


    @Override
    public String getInfo() {
        return "Write an \"import_gsheet\" if you want to save change to google sheets";
    }

    @Override
    public void doCommand() {
        try {
            FileConnection fileConnection = FileConnection.getInstance("stock_import_csv1.csv");
            FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
            GSheetStorageUtils gSheetStorageUtils = new GSheetStorageUtils(GSheetConnection.getSheetsService());

            for(Product product : gSheetStorageUtils.getDataFromGSheet()){
                fileStorageUtils.addProduct(product);
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: Can't connect to GSHEET");
        }
    }
}
