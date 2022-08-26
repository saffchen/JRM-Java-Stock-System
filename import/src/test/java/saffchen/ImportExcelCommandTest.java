package saffchen;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

class ImportExcelCommandTest {
    private final InputStream systemIn = System.in;

    @Test
    void doImportExcelCommand() throws GeneralSecurityException, IOException {
        ImportExcelCommand command = new ImportExcelCommand();
        System.setIn(new ByteArrayInputStream("stock.xlsx\n".getBytes(StandardCharsets.UTF_8)));
        command.doCommand();
        System.setIn(systemIn);
    }
}
