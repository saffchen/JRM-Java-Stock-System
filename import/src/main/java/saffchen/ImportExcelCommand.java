package saffchen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saffchen.command.Command;
import saffchen.command.Exit;
import saffchen.command.ExitFromCommandMenu;
import saffchen.database.ExcelConnection;
import saffchen.database.FileConnection;
import saffchen.utils.ExcelImportUtils;
import saffchen.utils.FileStorageUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.Scanner;

public class ImportExcelCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImportExcelCommand.class);
    private Exit exit;

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    @Override
    public String getInfo() {
        return "Write an \"import_excel\" if you want to save change to excel";
    }

    @Override
    public void doCommand() throws GeneralSecurityException, IOException {
        LOGGER.info(" --- IMPORT_EXCEL ---");
        try {
            String fileName = null;
            do {
                System.out.print("Enter the name of XLSX file or EXIT: ");
                fileName = new Scanner(System.in).nextLine();
                if (fileName.equals("exit")) {
                    setExit(new ExitFromCommandMenu());
                    exit.doExit();
                }
            } while (!Files.exists(Path.of(fileName)));
            ExcelConnection fileExcelConnection = ExcelConnection.getInstance(fileName);
            FileConnection fileCsvConnection = FileConnection.getInstance("stock_import_csv.csv");

            FileStorageUtils fileStorageUtils = new FileStorageUtils(fileCsvConnection);
            ExcelImportUtils excelImportUtils = new ExcelImportUtils(fileExcelConnection);

            String result = fileStorageUtils.addRawProductsFromListToCSV(excelImportUtils.checkTheDuplicates(
                    excelImportUtils.getData(),
                    fileStorageUtils.getDataFromCSV())
            );
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error: Can't get data for import");
        }
    }
}
