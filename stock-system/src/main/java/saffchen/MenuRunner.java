package saffchen;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import saffchen.entities.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class MenuRunner {
    public static void main(String[] args) {
        SpringApplication.run(MenuRunner.class, args);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(new ProductEntity());
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
