package saffchen.command;

public class GenerateReportCommand implements Command {
    private ReceiverDB receiverDB;

    public GenerateReportCommand(ReceiverDB receiverDB) {
        this.receiverDB = receiverDB;
    }

    @Override
    public void doCommand() {
        receiverDB.createReport();
    }
}
