package saffchen.command;

public class ShowAllCommand implements Command {
    private Receiver receiver;

    public ShowAllCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void doCommand() {
        receiver.showAll();
    }
}
