package saffchen.command;

import saffchen.database.ExcelConnection;
import saffchen.database.FileConnection;
import saffchen.utils.ExcelImportUtils;
import saffchen.utils.FileStorageUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Scanner;

public class ImportExcelCommand implements Command {
    @Override
    public String getInfo() {
        return "Write an \"import_excel\" if you want to save change to excel";
    }

    @Override
    public void doCommand() throws GeneralSecurityException, IOException {

        try {
            String filePath = null;
            ExcelConnection fileExcelConnection = ExcelConnection.getInstance("c:\\work\\jrm store\\stock_import_csv.xlsx");
            FileConnection fileCsvConnection = FileConnection.getInstance("stock_import_csv.csv");

            FileStorageUtils fileStorageUtils = new FileStorageUtils(fileCsvConnection);
            ExcelImportUtils excelImportUtils = new ExcelImportUtils(fileExcelConnection);

            fileStorageUtils.addRawProductsFromListToCSV(excelImportUtils.checkTheDublicates(
                    excelImportUtils.getData(),
                    fileStorageUtils.getDataFromCSV())
            );

            System.out.println("Data were imported successfully!");

        } catch (Exception e) {
            System.out.println("Error: Can't get data for import");;
        }
    }
}
