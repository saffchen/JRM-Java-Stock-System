package saffchen;

import saffchen.reports.GenerateReportCommand;

/**
 * @author saffchen created on 14.08.2022
 */
public class main {
    public static void main(String[] args) throws Exception {
        GenerateReportCommand generateReportCommand = new GenerateReportCommand();
        generateReportCommand.doCommand();
    }
}
