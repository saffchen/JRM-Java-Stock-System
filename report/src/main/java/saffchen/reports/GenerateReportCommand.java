package saffchen.reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saffchen.command.Command;
import saffchen.database.FileConnection;
import saffchen.theme.ThemeSelectionService;
import saffchen.utils.FileStorageUtils;

import java.util.Scanner;

public class GenerateReportCommand implements Command {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(GenerateReportCommand.class);

    @Override
    public void doCommand() {
        LOGGER.info(" --- GENERATE_REPORT ---");
        Scanner scanner = new Scanner(System.in);
        FileStorageUtils fileStorageUtils = new FileStorageUtils(
                FileConnection.getInstance("stock_import_csv.csv"));

        String criteria;
        String header;
        while (true) {
            System.out.println("*** REPORT ***");
            System.out.println("Possible values: ");

            for (String h : fileStorageUtils.getHeadersFromCSV()) {
                System.out.print("| " + h + " |");
            }

            System.out.print("\nEnter the field to search or EXIT: ");
            header = scanner.next().trim().toUpperCase();

            while (true) {
                System.out.println("*** Searching  by " + header + " ***");
                System.out.print("Enter the KEYWORD or EXIT: ");
                criteria = scanner.next().trim().toUpperCase();
                try {
                    header = header.charAt(0) + header.substring(1, header.length()).toLowerCase();
                    PDFReportFromFile report = new PDFReportFromFile(header, criteria);
                    report.generateReport(ThemeSelectionService.THEMES.iterator().next().getName());
                } catch (Exception e) {
                    System.out.println("Error: Can't create the report! Try again!");
                }
            }
        }
    }
}