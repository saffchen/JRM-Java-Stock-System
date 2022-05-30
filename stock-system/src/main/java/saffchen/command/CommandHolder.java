package saffchen.command;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandHolder {
    private final Map<String, Command> commandHolder = new LinkedHashMap<>();

    public Map<String, Command> getPreparedCommandHolder(){
        addCommand("ADD_PRODUCT", new AddCommand());
        addCommand("DELETE_PRODUCT", new DeleteCommand());
        addCommand("MODIFY_PRODUCT", new ModifyCommand());
        addCommand("EXPORT_EXCEL", new CreateXlsFileCommand());
        addCommand("SHOW_ALL", new ShowAllCommand());
        addCommand("GENERATE_REPORT", new GenerateReportCommand());
        addCommand("IMPORT_GSHEET", new ImportFromGSheetCommand());
        addCommand("EXIT", new ExitCommand());

        return this.commandHolder;
    }

    public void addCommand(String key, Command command) {
        try {
            commandHolder.put(key, command);
        } catch (Exception e) {
            System.out.println("Error: Can't add the command");
        }
    }

    public void printCommandInfo(){
        System.out.println("Welcome to the Stock System");
        System.out.println("*******************************************************************************\n");
        for(Map.Entry<String, Command> entry : this.getPreparedCommandHolder().entrySet()){
            System.out.println("* " + entry.getValue().getInfo());
        }
        System.out.println("\n*******************************************************************************\n");
    }
}
