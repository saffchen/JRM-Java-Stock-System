package saffchen.utils;

import saffchen.database.Connection;
import saffchen.product.Product;

public interface StorageUtils {
    Connection I_CONNECTION = null;

    public void addProduct(Product product);

    public void deleteProduct();

    public void modifyProduct();

    public void showAllProducts();

}
