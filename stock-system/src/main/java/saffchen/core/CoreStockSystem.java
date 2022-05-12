package saffchen.core;

import java.io.IOException;
import java.util.Scanner;

import static saffchen.command.Command.*;

public class CoreStockSystem {

    public void onEnteringCommand() {
        while (true) {
            System.out.println("Write an \"exit\" to exit the application\n");
            try {
                Scanner scanner = new Scanner(System.in);
                String enteringCommand = scanner.nextLine();
                switch (enteringCommand.toUpperCase()) {
                    case MODIFY_PRODUCT -> System.out.println("modify");
                    case DELETE_PRODUCT -> System.out.println("delete");
                    case SHOW_ALL -> System.out.println("show");
                    case IMPORT_CSV -> System.out.println("csv");
                    case IMPORT_EXCEL -> System.out.println("excel");
                    case IMPORT_GSHEET -> System.out.println("gsheet");
                    case GENERATE_REPORT -> System.out.println("report");
                    case EXIT -> {
                        System.out.println("Have a good day!");
                        System.exit(0);
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command");
                continue;
            }
        }
    }
}