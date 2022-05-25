package saffchen.command;

public class ImportFromGSheetCommand implements Command{

    private ReceiverDB receiverDB;

    public ImportFromGSheetCommand(ReceiverDB receiverDB) {
        this.receiverDB = receiverDB;
    }

    @Override
    public void doCommand() {
        receiverDB.importFromGsheet();
    }
}
