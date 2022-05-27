package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.reports.PDFReportFromFile;
import saffchen.utils.FileStorageUtils;

import java.util.Scanner;

public class GenerateReportCommand implements Command {

    @Override
    public void doCommand() {
        Scanner scanner = new Scanner(System.in);
        FileStorageUtils fileStorageUtils = new FileStorageUtils(
                FileConnection.getInstance("stock_import_csv.csv"));

        String criteria;
        String header;
        while (true) {
            System.out.println("*** REPORT ***");
            System.out.println("Possible values: ");

            for (String h : fileStorageUtils.getHeadersFromCSV())
                System.out.print("| " + h + " |");

            System.out.print("\nEnter the field to search or EXIT: ");
            header = scanner.next().trim().toUpperCase();
            if (header.equals("EXIT"))
                break;

            while (true) {
                System.out.println("*** Searching  by " + header + " ***");
                System.out.print("Enter the KEYWORD or EXIT: ");
                criteria = scanner.next().trim().toUpperCase();
                if (criteria.equals("EXIT"))
                    break;
                try {
                    header = header.substring(0, 1) + header.substring(1, header.length()).toLowerCase();
                    PDFReportFromFile report = new PDFReportFromFile(header, criteria);
                    report.generateReport();
                } catch (Exception e) {
                    System.out.println("Error: Can't create the report! Try again!");
                }
            }
        }
    }
}