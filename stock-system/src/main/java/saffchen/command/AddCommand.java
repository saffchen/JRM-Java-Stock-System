package saffchen.command;

import saffchen.product.Product;

public class AddCommand implements Command {
    private Receiver receiver;
    private Product product;

    public AddCommand(Receiver receiver, Product product) {
        this.receiver = receiver;
        this.product = product;
    }

    @Override
    public void doCommand() {
        receiver.addProduct(product);
    }

}
