package saffchen.command;

public class DeleteCommand implements Command{
    private ReceiverDB receiverDB;

    public DeleteCommand(ReceiverDB receiverDB) {
        this.receiverDB = receiverDB;
    }

    @Override
    public void doCommand() {
        receiverDB.deleteProduct();
    }
}
