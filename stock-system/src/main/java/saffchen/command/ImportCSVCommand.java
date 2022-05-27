package saffchen.command;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ImportCSVCommand implements Command{
    @Override
    public String getInfo() {
        return "7 Write an \"import_csv\" if you want to save change to csv";
    }

    @Override
    public void doCommand() throws GeneralSecurityException, IOException {

    }
}
