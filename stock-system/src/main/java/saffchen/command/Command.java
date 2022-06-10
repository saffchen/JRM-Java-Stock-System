package saffchen.command;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface Command {
    String getInfo();
    void doCommand() throws Exception;
}
