package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.database.GSheetConnection;
import saffchen.utils.FileStorageUtils;
import saffchen.utils.GSheetImportUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ImportFromGSheetCommand implements Command {


    @Override
    public String getInfo() {
        return "Write an \"import_gsheet\" if you want to save change to google sheets";
    }

    @Override
    public void doCommand() {
        try {
            FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
            FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
            GSheetImportUtils gSheetImportUtils = new GSheetImportUtils(GSheetConnection.getSheetsService());
            fileStorageUtils.addRawProductsFromListToCSV(gSheetImportUtils.checkTheDublicates(
                    gSheetImportUtils.getData(),
                    fileStorageUtils.getDataFromCSV()));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: Can't connect to GSHEET");
        }
    }
}
