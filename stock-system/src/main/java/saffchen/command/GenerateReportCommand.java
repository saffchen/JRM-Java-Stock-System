package saffchen.command;

import saffchen.reports.PdfGenerator;
import saffchen.reports.ReportGenerator;

public class GenerateReportCommand implements Command{
    private ReceiverDB receiverDB;

    public GenerateReportCommand(ReceiverDB receiverDB) {
        this.receiverDB = receiverDB;
    }
    @Override
    public void doCommand() {
        receiverDB.create_report();
    }
}
