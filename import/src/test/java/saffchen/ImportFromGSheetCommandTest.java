package saffchen;

import org.junit.jupiter.api.Test;

public class ImportFromGSheetCommandTest {
    @Test
    void doImportFromGSheetCommand() {
        new ImportFromGSheetCommand().doCommand();
    }
}
