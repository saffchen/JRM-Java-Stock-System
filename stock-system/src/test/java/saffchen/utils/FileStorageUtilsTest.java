package saffchen.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;
import saffchen.database.FileConnection;
import saffchen.entities.ProductEntity;
import saffchen.entities.SatelliteEntity;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileStorageUtilsTest {
    @Test
    void getDataFromCSV() {

    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void addHeadersToCSV(String headers) {
        PrintStream originalOut = System.out;
        String consoleOutput = "";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream(200);
            PrintStream capture = new PrintStream(out);
            System.setOut(capture);
            FileConnection fc = Mockito.mock(FileConnection.class);
            FileStorageUtils fsu = new FileStorageUtils(fc);
            fsu.addHeadersToCSV(headers);
            capture.flush();
            consoleOutput = out.toString();
            System.setOut(originalOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("Error: There are no headers in the string!", consoleOutput.trim());
    }


    @Test

    void addProduct() {
        ProductEntity product = new ProductEntity(0L,"title", "description", 11111.1, List.of("tag1", "tag2"), "category",6, new SatelliteEntity("satellite"));
        FileConnection fc = FileConnection.getInstance("test.csv");
        FileStorageUtils fsu = new FileStorageUtils(fc);

        PrintStream originalOut = System.out;
        String consoleOutput = "";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream(200);
            PrintStream capture = new PrintStream(out);
            System.setOut(capture);
            fsu.addProduct(product);
            capture.flush();
            consoleOutput = out.toString();
            System.setOut(originalOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("Product was added to database successfully!", consoleOutput.trim());
    }

    @Test
    void addRawProductsFromListToCSV() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void modifyProduct() {
    }

    @Test
    void showAllProducts() {
    }

    @Test
    void getProductByTitle() {
    }

    @Test
    void getCSVParser() {
    }

    @Test
    void getDataForReportFromCSV() {
    }
}