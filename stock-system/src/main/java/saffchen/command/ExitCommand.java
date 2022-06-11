package saffchen.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExitCommand implements Command {
    @Override
    public String getInfo(){
        return "Write an \"exit\" to exit the application";
    }

    @Override
    public void doCommand() {
        System.out.println("Have a good day!");
        System.exit(0);
    }
}
