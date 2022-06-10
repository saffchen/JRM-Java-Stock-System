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
    private Exit exit;

    private void setExit(Exit exit) {
        this.exit = exit;
    }
    @Override
    public String getInfo() {
        return "Write an \"import_excel\" if you want to save change to excel";
    }

    @Override
    public void doCommand() throws GeneralSecurityException, IOException {

        try {
            String fileName = null;
            do{
                System.out.print("Enter the name of XLSX file or EXIT: ");
                fileName = new Scanner(System.in).nextLine();
                if (fileName.equals("exit")) {
                    setExit(new ExitFromCommandMenu());
                    exit.doSmth();
                }
            } while(!Files.exists(Path.of(fileName)));
            ExcelConnection fileExcelConnection = ExcelConnection.getInstance(fileName);
            FileConnection fileCsvConnection = FileConnection.getInstance("stock_import_csv.csv");

            FileStorageUtils fileStorageUtils = new FileStorageUtils(fileCsvConnection);
            ExcelImportUtils excelImportUtils = new ExcelImportUtils(fileExcelConnection);

            String result = fileStorageUtils.addRawProductsFromListToCSV(excelImportUtils.checkTheDublicates(
                    excelImportUtils.getData(),
                    fileStorageUtils.getDataFromCSV())
            );
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error: Can't get data for import");;
        }
    }
}
