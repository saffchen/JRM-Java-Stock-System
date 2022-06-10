package saffchen.command;

public class ExitCommand implements Command {
    private Exit exit;

    public void setExit(Exit exit) {
        this.exit = exit;
    }

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
