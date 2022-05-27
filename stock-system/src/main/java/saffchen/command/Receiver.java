package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.database.GSheetConnector;
import saffchen.product.Product;
import saffchen.reports.PDFReportFromFile;
import saffchen.utils.FileStorageUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Receiver {

    void addProduct(Product product) {

    }

    public void modifyProduct() {
        System.out.println("Modified the product...");
    }

    public void deleteProduct() {
        System.out.println("Deleting the product...");
    }

    public void showAll() {

    }

    public void importFromGsheet() {
        //nop
    }

    public void createReport() {

    }
}
