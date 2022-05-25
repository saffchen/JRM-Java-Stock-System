package saffchen.command;

import saffchen.product.Product;
import java.util.HashMap;
import java.util.Map;

public class CommandHolder {
    private final Receiver receiver = new Receiver();
    private final Map<String, Command> commandHolder = new HashMap<>();

    public Map<String, Command> getPreparedCommandHolder(){
        addCommand("ADD_PRODUCT", new AddCommand(receiver, new Product()));
        addCommand("DELETE_PRODUCT", new DeleteCommand(receiver));
        addCommand("MODIFY_PRODUCT", new ModifyCommand(receiver));
        addCommand("EXPORT_EXCEL", new CreateXlsFileCommand());
        addCommand("SHOW_ALL", new ShowAllCommand(receiver));
        addCommand("GENERATE_REPORT", new GenerateReportCommand(receiver));
        addCommand("IMPORT_GSHEET", new ImportFromGSheetCommand(receiver));
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
}
