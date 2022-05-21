package saffchen.command;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface Command {
    void doCommand() throws GeneralSecurityException, IOException;
}
