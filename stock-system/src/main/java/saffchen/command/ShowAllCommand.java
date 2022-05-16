package saffchen.command;

public class ShowAllCommand implements Command{
    private ReceiverDB receiverDB;

    public ShowAllCommand(ReceiverDB receiverDB) {
        this.receiverDB = receiverDB;
    }

    @Override
    public void doCommand() {
        receiverDB.show_all();
    }
}
