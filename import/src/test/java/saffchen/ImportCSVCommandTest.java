package saffchen;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * @author saffchen created on 03.08.2022
 */

class ImportCSVCommandTest {

    @Test
    void doImportCSVCommandTest() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        ClassLoader classLoader = getClass().getClassLoader();
        FileReader reader = new FileReader((Objects.requireNonNull(classLoader.getResource("stock_import_csv.csv"))).getFile());
        while (reader.ready()) {
            stringBuilder.append((char) reader.read());
        }
        System.out.println(stringBuilder);
    }
}