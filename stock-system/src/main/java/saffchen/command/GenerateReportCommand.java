package saffchen.command;
public class GenerateReportCommand implements Command {
    private Receiver receiver;

    public GenerateReportCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void doCommand() {
        receiver.createReport();
    }
}