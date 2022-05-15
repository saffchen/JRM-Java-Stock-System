package saffchen.database;

import saffchen.product.Product;

public class Database {

    void add_product(Product product){
        System.out.println("Adding the product...");
    };

    public void modify_product(){
        System.out.println("Modified the product...");
    };

    void delete_product(){
        System.out.println("Deleting the product...");
    };

    public void show_all(){
        System.out.println("Selecting the product...");
    };

}
