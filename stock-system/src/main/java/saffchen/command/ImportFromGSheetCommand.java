package saffchen.command;

public class ImportFromGSheetCommand implements Command{

    private Receiver receiver;

    public ImportFromGSheetCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void doCommand() {
        receiver.importFromGsheet();
    }
}
