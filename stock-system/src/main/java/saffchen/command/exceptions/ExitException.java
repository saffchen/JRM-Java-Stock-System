package saffchen.command.exceptions;

public class ExitException extends Exception {
    public ExitException() throws Exception {
        Exception e = new Exception();
        throw e;
    }
}

