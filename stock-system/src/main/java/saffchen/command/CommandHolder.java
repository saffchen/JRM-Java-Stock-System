package saffchen.command;

import saffchen.product.Product;

import java.util.HashMap;
import java.util.Map;

public class CommandHolder {
    private final ReceiverDB receiverDB = new ReceiverDB();
    private final Map<String, Command> commandHolder = new HashMap<>();

    public Map<String, Command> getPreparedCommandHolder() {
        addCommand("ADD_PRODUCT", new AddCommand(receiverDB, new Product()));
        addCommand("DELETE_PRODUCT", new DeleteCommand(receiverDB));
        addCommand("MODIFY_PRODUCT", new ModifyCommand(receiverDB));
        addCommand("SHOW_ALL", new ShowAllCommand(receiverDB));
        addCommand("GENERATE_REPORT", new GenerateReportCommand(receiverDB));
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
