package saffchen.command;

public class ModifyCommand implements Command{
    private ReceiverDB receiverDB;

    public ModifyCommand(ReceiverDB receiverDB) {
        this.receiverDB = receiverDB;
    }

    @Override
    public void doCommand() {
        receiverDB.modify_product();
    }
}
