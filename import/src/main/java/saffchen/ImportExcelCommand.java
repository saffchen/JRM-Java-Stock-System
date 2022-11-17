package saffchen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import saffchen.command.Command;
import saffchen.database.ExcelConnection;
import saffchen.database.FileConnection;
import saffchen.utils.ExcelImportUtils;
import saffchen.utils.FileStorageUtils;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ImportExcelCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImportExcelCommand.class);

    @Override
    public void doCommand() {
        LOGGER.info(" --- IMPORT_EXCEL ---");
        try {
            String fileName = null;
            do {
                System.out.print("Enter the name of XLSX file: ");
                fileName = new Scanner(System.in).nextLine();
            } while (!fileExists(fileName));
            ExcelConnection fileExcelConnection = ExcelConnection.getInstance(fileName);
            FileConnection fileCsvConnection = FileConnection.getInstance("stock_import_csv.csv");

            FileStorageUtils fileStorageUtils = new FileStorageUtils(fileCsvConnection);
            ExcelImportUtils excelImportUtils = new ExcelImportUtils(fileExcelConnection);

            String result = fileStorageUtils.addRawProductsFromListToCSV(excelImportUtils.checkTheDuplicates(
                    excelImportUtils.getData(),
                    fileStorageUtils.getDataFromCSV())
            );
            LOGGER.info(result);
        } catch (Exception e) {
            LOGGER.error("Error: Can't get data for import");
        }
    }

    private boolean fileExists(String fileName) {
        boolean result = true;
        try {
            ResourceUtils.getFile("classpath:" + fileName);
        } catch (FileNotFoundException e) {
            result = false;
        }
        Path path = Path.of(fileName);
        return (Files.exists(path) && Files.isRegularFile(path)) || result;
    }
}
