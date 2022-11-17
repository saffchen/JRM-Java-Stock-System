package saffchen.command;

public interface Command {
    String getInfo();

    void doCommand() throws Exception;
}