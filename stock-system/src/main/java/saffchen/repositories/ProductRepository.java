package saffchen.repositories;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void save(ProductEntity productEntity){
        entityManager.persist(productEntity);
    }
}
