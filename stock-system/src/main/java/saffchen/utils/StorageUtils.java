package saffchen.utils;

import saffchen.database.Connection;
import saffchen.product.Product;

public interface StorageUtils {

    public void addProduct(Product product);

    public void deleteProduct(Product product);

    public void modifyProduct(Product productBefore, Product productAfter);

    public void showAllProducts();

}
