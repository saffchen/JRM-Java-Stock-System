package saffchen.command;

import saffchen.reports.PDFReportFromFileBySatellite;

import java.util.Scanner;

public class GenerateReportCommand implements Command {

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

    @Override
    public void doCommand() {
        createReport();
    }
}
