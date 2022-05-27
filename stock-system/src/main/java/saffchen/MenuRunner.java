package saffchen;

import saffchen.command.*;
import saffchen.greeting_message.GreetingMessage;
import saffchen.utils.*;


import java.util.Map;
import java.util.Scanner;

public class MenuRunner {
    public static void main(String[] args) {
        CommandHolder holder = new CommandHolder();
        GreetingMessage message = new GreetingMessage();
        Scanner scanner = new Scanner(System.in);

        System.out.println(FileUtils.getBanner());
        System.out.println(FileUtils.getParticipants());

        message.printGreetingMessages();

        String inputCommand = "";
        while (true) {
            try {
                System.out.print("Enter the command: ");
                inputCommand = scanner.next().trim().toUpperCase();
                for (Map.Entry<String, Command> entry : holder.getPreparedCommandHolder().entrySet()) {
                    if (inputCommand.equals(entry.getKey())) {
                        entry.getValue().doCommand();
                    }
                }
            } catch (Exception e) {
                System.out.println("Unsupported operation \"" + inputCommand + "\". Try again.");
            }
        }
    }
}