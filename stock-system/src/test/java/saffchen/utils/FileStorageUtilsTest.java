package saffchen.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import saffchen.database.FileConnection;
import saffchen.product.Product;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FileStorageUtilsTest {

    @Test
    void getHeadersFromCSV() {
        FileStorageUtils fsu = Mockito.mock(FileStorageUtils.class);
        when(fsu.getHeadersFromCSV()).thenReturn(new ArrayList<String>(
                Arrays.asList("Title", "Description", "Price ($)", "Tags", "Category", "Count", "Satellite")));
        ArrayList<String> headers = new ArrayList<String>(
                Arrays.asList("Title", "Description", "Price ($)", "Tags", "Category", "Count", "Satellite"));

        assertEquals(headers, fsu.getHeadersFromCSV());
    }
    @Test
    void getNullHeadersFromCSV() {
        FileStorageUtils fsu = Mockito.mock(FileStorageUtils.class);
        when(fsu.getHeadersFromCSV()).thenReturn(null);
        assertNull(fsu.getHeadersFromCSV());
    }

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
        Product product = new Product("title", "description", 11111.1, new ArrayList<String>(), "category",6, "satellite");
        FileConnection fc = Mockito.mock(FileConnection.class);
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