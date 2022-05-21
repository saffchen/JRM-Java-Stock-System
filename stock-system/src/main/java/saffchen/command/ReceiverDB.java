package saffchen.command;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import saffchen.product.Product;
import saffchen.reports.PDFReportFromFileBySatellite;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static saffchen.export_excel.Create_Excel.SPEADSHEET_ID;
import static saffchen.export_excel.Create_Excel.getSheetsService;

public class ReceiverDB {

    public void addProduct(Product product) {
        System.out.println("Adding the product...");
    }

    public void modifyProduct() {
        System.out.println("Modified the product...");
    }

    public void deleteProduct() {
        System.out.println("Deleting the product...");
    }

    public void showAll() {
        System.out.println("Selecting the product...");
    }

    public void createReport() {
        Scanner scanSatellite = new Scanner(System.in);
        String criteria;
        while (true) {
            System.out.println("*** REPORT ***");
            System.out.print("Enter the satellite or EXIT: ");
            criteria = scanSatellite.next().trim().toUpperCase();
            if (criteria.equals("EXIT"))
                break;
            try {
                PDFReportFromFileBySatellite report = new PDFReportFromFileBySatellite(criteria);
                report.generateReport();
            } catch (Exception e) {
                System.out.println("Error: Can't create the report! Try again!");
            }
        }
    }

}