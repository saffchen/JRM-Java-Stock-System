package saffchen.command;

public class ExitFromCommandMenu implements Exit {
    @Override
    public void doExit() throws Exception {
        throw new Exception("Exit");
    }
}