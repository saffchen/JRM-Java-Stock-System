package saffchen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saffchen.command.Command;
import saffchen.database.FileConnection;
import saffchen.database.GSheetConnection;
import saffchen.utils.FileStorageUtils;
import saffchen.utils.GSheetImportUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class ImportFromGSheetCommand implements Command {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ImportFromGSheetCommand.class);

    private static final List<String> DEPRECATED_SYMBOLS = List.of("/", "|", "?", "*", "<", ">", "!");

    @Override
    public String getInfo() {
        return "Write an \"import_gsheet\" if you want to save change to google sheets";
    }

    @Override
    public void doCommand() {
        LOGGER.info(" --- IMPORT_GSHEET ---");
        try {
            FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
            FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);

            GSheetImportUtils gSheetImportUtils = new GSheetImportUtils(GSheetConnection.getSheetsService());
            fileStorageUtils.addRawProductsFromListToCSV(gSheetImportUtils.checkTheDuplicates(
                    gSheetImportUtils.getData(),
                    fileStorageUtils.getDataFromCSV()));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: Can't connect to GSHEET");
        }
    }

    private boolean isNameCorrect(String name) {
        for (String deprecatedSymbol : DEPRECATED_SYMBOLS) {
            if (name.contains(deprecatedSymbol)) {
                System.err.println("Ошибка, введите название корректно!");
                return false;
            }
        }
        return true;
    }
}
