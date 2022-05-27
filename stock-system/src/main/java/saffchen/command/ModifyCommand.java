package saffchen.command;

public class ModifyCommand implements Command {
    private Receiver receiver;

    public ModifyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void doCommand() {
        receiver.modifyProduct();
    }
}
