package saffchen.command;

import saffchen.command.exceptions.ExitException;

public class ExitFromComandMenu implements Exit{
    @Override
    public void doSmth() throws Exception {
        throw new ExitException();
    }
}
