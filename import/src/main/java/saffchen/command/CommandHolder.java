package saffchen.command;

import saffchen.ImportCSVCommand;
import saffchen.ImportExcelCommand;
import saffchen.ImportFromGSheetCommand;
import saffchen.export_excel.CreateXlsFileCommand;
import saffchen.reports.GenerateReportCommand;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandHolder {
    private final Map<String, Command> commandHolder;

    {
        commandHolder = new LinkedHashMap<>();
        addCommand("ADD_PRODUCT", new AddCommand());
        addCommand("DELETE_PRODUCT", new DeleteCommand());
        addCommand("MODIFY_PRODUCT", new ModifyCommand());
        addCommand("EXPORT_EXCEL", new CreateXlsFileCommand());
        addCommand("SHOW_ALL", new ShowAllCommand());
        addCommand("GENERATE_REPORT", new GenerateReportCommand());
        addCommand("IMPORT_GSHEET", new ImportFromGSheetCommand());
        addCommand("IMPORT_EXCEL", new ImportExcelCommand());
        addCommand("IMPORT_CSV", new ImportCSVCommand());
        addCommand("ADD_SATELLITE", new AddSatellite());
        addCommand("DELETE_SATELLITE", new DeleteSatellite());
        addCommand("EXIT", new ExitCommand());
    }

    public Map<String, Command> getCommandHolder() {
        return commandHolder;
    }

    public void addCommand(String key, Command command) {
        try {
            commandHolder.put(key, command);
        } catch (Exception e) {
            System.out.println("Error: Can't add the command");
        }
    }

    public void printCommandInfo() {
        System.out.println("Welcome to the Stock System");
        System.out.println("*******************************************************************************\n");
        for (Map.Entry<String, Command> entry : this.getCommandHolder().entrySet()) {
            System.out.println("* " + entry.getValue().getInfo());
        }
        System.out.println("\n*******************************************************************************\n");
    }
}
