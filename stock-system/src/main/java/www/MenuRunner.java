package www;

import java.util.Map;
import java.util.Scanner;

public class MenuRunner {
    public static void main(String[] args) {
        CommandHolder holder = new CommandHolder();
        Database database = new Database();
        GreetingMessage message = new GreetingMessage();

        holder.addCommand("ADD_PRODUCT", new AddCommand(database,new Product()));
        holder.addCommand("DELETE_PRODUCT", new DeleteCommand(database));
        holder.addCommand("MODIFY_PRODUCT", new ModifyCommand(database));
        holder.addCommand("SHOW_ALL", new ShowAllCommand(database));
        holder.addCommand("EXIT", new ExitCommand());

        message.printGreetingMessages();

        while(true) {
            try {
                System.out.print("Enter the command: ");
                String inputCommand = new Scanner(System.in).next().trim().toUpperCase();
                for (Map.Entry<String, Command> entry : holder.getCommandHolder().entrySet()) {
                    if (inputCommand.equals(entry.getKey())) {
                        entry.getValue().doCommand();
                    }
                }
            }catch(Exception e){
                System.out.println("Unsupported operation. Try again.");
            }
        }
    }
}