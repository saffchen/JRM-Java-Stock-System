package saffchen.command;

public class ImportFromGSheetCommand implements Command{

    @Override
    public String getInfo() {
        return "* Write an \"import_gsheet\" if you want to save change to google sheets";
    }

    @Override
    public void doCommand() {

    }
}
