package saffchen.command;

public class DeleteCommand implements Command {
    private Receiver receiver;

    public DeleteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void doCommand() {
        receiver.deleteProduct();
    }
}
