package saffchen.command;

public class ExitCommand implements Command {
    @Override
    public String getInfo(){
        return "1 Write an \"exit\" to exit the application";
    }

    @Override
    public void doCommand() {
        System.exit(0);
    }
}
