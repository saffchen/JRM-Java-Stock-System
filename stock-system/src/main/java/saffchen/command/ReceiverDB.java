package saffchen.command;

import saffchen.product.Product;
import saffchen.reports.PDFReportFromFileBySatellite;

import java.util.Scanner;

public class ReceiverDB {

    void addProduct(Product product) {
        System.out.println("Adding the product...");
    }

    public void modifyProduct() {
        System.out.println("Modified the product...");
    }

    void deleteProduct() {
        System.out.println("Deleting the product...");
    }

    public void showAll() {
        System.out.println("Selecting the product...");
    }

    public void createReport() {
        Scanner scanSatellite = new Scanner(System.in);
        String criteria;
        while(true){
            System.out.println("*** REPORT ***");
            System.out.print("Enter the satellite or EXIT: ");
            criteria = scanSatellite.next().trim().toUpperCase();
            if (criteria.equals("EXIT"))
                break;
            try {
                PDFReportFromFileBySatellite report = new PDFReportFromFileBySatellite(criteria);
                report.generateReport();
            } catch (Exception e){
                System.out.println("Error: Can't create the report! Try again!");
            }
        }
    }

}
