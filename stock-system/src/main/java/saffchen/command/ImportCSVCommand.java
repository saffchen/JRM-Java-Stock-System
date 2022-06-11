package saffchen.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saffchen.utils.FileUtils;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;

public class ImportCSVCommand implements Command {
    private static final Logger logger
            = LoggerFactory.getLogger(ImportCSVCommand.class);
    @Override
    public String getInfo() {
        return "Write an \"import_csv\" if you want to save change to csv";
    }

    @Override
    public void doCommand() throws GeneralSecurityException, IOException {
        logger.info(" --- IMPORT_CSV ---");
        try {
        List<String> listCSV = List.of(FileUtils.getInfoFromTxtFile("stock_import_csv.csv"));
            FileWriter fileWriter = new FileWriter(new File(System.getProperty("user.dir"), "csvFromDB.csv"));
            for (String product : listCSV) {
                fileWriter.write(product + " " + System.getProperty("line.separator"));
                System.out.println("Success! File saved: " + System.getProperty("user.dir"));
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File is not found! Please, try again!");
        }
    }
}