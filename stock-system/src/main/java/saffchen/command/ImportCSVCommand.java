package saffchen.command;

import saffchen.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class ImportCSVCommand implements Command {
    @Override
    public String getInfo() {
        return "Write an \"import_csv\" if you want to save change to csv";
    }

    @Override
    public void doCommand() throws GeneralSecurityException, IOException {

        List<String> listCSV = List.of(FileUtils.getInfoFromTxtFile("stock_import_csv.csv"));
        try {
            FileWriter fileWriter = new FileWriter(new File(System.getProperty("user.dir"), "csvFromDB.csv"));
            for (String product : listCSV) {
                fileWriter.write(product + " " + System.getProperty("line.separator"));
                System.out.println("Success " + System.getProperty("user.dir"));
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}