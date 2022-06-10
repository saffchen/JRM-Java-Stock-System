package saffchen.command;

public class ExitFromApp implements Exit{
    @Override
    public void doSmth() {
        System.out.println("Have a good day!");
        System.exit(0);
    }
}
