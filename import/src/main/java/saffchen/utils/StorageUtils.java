package saffchen.utils;

import saffchen.entities.ProductEntity;

public interface StorageUtils {

    public void addProduct(ProductEntity product);

    public void deleteProduct(ProductEntity product);

    public void modifyProduct(ProductEntity before, ProductEntity after);

    public void showAllProducts();

}
