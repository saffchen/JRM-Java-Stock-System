package saffchen.utils;

import saffchen.database.IConnection;
import saffchen.product.Product;

public interface StorageUtils {
    IConnection I_CONNECTION = null;

    public void addProduct(Product product);

    public void deleteProduct();

    public void modifyProduct();

    public void showAllProducts();

}
