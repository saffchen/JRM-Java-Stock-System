package saffchen;

import saffchen.command.*;
import saffchen.command.ReceiverDB;
import saffchen.greeting_message.GreetingMessage;
import saffchen.product.Product;
import saffchen.utils.FileUtils;

import java.util.Map;
import java.util.Scanner;

public class MenuRunner {
    public static void main(String[] args) {
        CommandHolder holder = new CommandHolder();
        ReceiverDB receiverDB = new ReceiverDB();
        GreetingMessage message = new GreetingMessage();

        holder.addCommand("ADD_PRODUCT", new AddCommand(receiverDB,new Product()));
        holder.addCommand("DELETE_PRODUCT", new DeleteCommand(receiverDB));
        holder.addCommand("MODIFY_PRODUCT", new ModifyCommand(receiverDB));
        holder.addCommand("SHOW_ALL", new ShowAllCommand(receiverDB));
        holder.addCommand("GENERATE_REPORT", new GenerateReportCommand(receiverDB));
        holder.addCommand("EXIT", new ExitCommand());

        FileUtils utils = new FileUtils();
        System.out.println(utils.getBanner());
        System.out.println(utils.getParticipants());

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