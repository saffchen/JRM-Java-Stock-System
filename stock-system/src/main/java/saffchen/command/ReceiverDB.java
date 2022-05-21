package saffchen.command;

import saffchen.product.Product;

public class ReceiverDB {

    public void addProduct(Product product) {
        System.out.println("Adding the product...");
    }

    public void modifyProduct() {
        System.out.println("Modified the product...");
    }

    public void deleteProduct() {
        System.out.println("Deleting the product...");
    }

    public void showAll() {
        System.out.println("Selecting the product...");
    }

}