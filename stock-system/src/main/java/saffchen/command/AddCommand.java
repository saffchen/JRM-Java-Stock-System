package saffchen.command;

import saffchen.product.Product;

public class AddCommand implements Command{
    private ReceiverDB receiverDB;
    private Product product;
    public AddCommand(ReceiverDB receiverDB, Product product) {
        this.receiverDB = receiverDB;
        this.product = product;
    }

    @Override
    public void doCommand() {
        receiverDB.addProduct(product);
    }
}
