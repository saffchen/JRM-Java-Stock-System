package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.reports.PDFReportFromFile;
import saffchen.utils.FileStorageUtils;
import saffchen.utils.MenuUtils;

import java.util.HashSet;
import java.util.Scanner;

public class GenerateReportCommand implements Command {

    @Override
    public String getInfo() {
        return "* Write a \"generate_report\" if you want to save pdf file with all positions";
    }

    @Override
    public void doCommand() {
        Scanner scanner = new Scanner(System.in);
        FileStorageUtils fileStorageUtils = new FileStorageUtils(
                FileConnection.getInstance("stock_import_csv.csv"));

        String criteria;
        String header = "";
        while (MenuUtils.isExit(header)) {
            System.out.println("*** REPORT ***");
            System.out.println("Possible values: ");

            for (String h : fileStorageUtils.getHeadersFromCSV())
                System.out.print("| " + h + " |");

            System.out.print("\nEnter the field to search or EXIT: ");
            header = scanner.next().trim().toUpperCase();

            while (MenuUtils.isExit(header)) {
                System.out.println("*** Searching  by " + header + " ***");
                System.out.print("Enter the KEYWORD or EXIT: ");
                criteria = scanner.next().trim().toUpperCase();

                try {
                    header = header.substring(0, 1) + header.substring(1, header.length()).toLowerCase();
                    PDFReportFromFile report = new PDFReportFromFile(header, criteria);
                    report.generateReport();
                } catch (Exception e) {
                    System.out.println("Error: Can't create the report! Enter correct criteries and try again!");
                }
            }
        }
    }
}