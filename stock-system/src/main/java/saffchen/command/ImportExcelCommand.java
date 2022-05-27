package saffchen.command;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ImportExcelCommand implements Command{
    @Override
    public String getInfo() {
        return "8 Write an \"import_excel\" if you want to save change to excel";
    }

    @Override
    public void doCommand() throws GeneralSecurityException, IOException {

    }
}
