package saffchen.dao;

import org.springframework.beans.factory.annotation.Autowired;
import saffchen.entities.ProductEntity;

import javax.persistence.EntityManager;

public class ProductDAO {
    @Autowired
    private EntityManager entityManager;

    public void saveProduct(ProductEntity productEntity){
        //nop
    }
}
