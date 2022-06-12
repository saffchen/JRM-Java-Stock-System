package saffchen.command;

public class ExitFromCommandMenu implements Exit {
    @Override
    public void doSmth() throws Exception {
        throw new Exception("Exit");
    }
}