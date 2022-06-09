package saffchen.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import saffchen.database.FileConnection;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void addHeadersToCSV() {
    }

    @Test
    void addProduct() {
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