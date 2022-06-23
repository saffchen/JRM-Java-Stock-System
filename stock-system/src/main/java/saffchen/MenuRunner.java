package saffchen;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import saffchen.entities.ProductEntity;
import saffchen.entities.SatelliteEntity;
import saffchen.repositories.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class MenuRunner {

    @Autowired
    private ProductRepository productRepository;
    public static void main(String[] args) {
        SpringApplication.run(MenuRunner.class, args);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setTitle("Ford Mustang");
        productEntity.setCategory("Cars");
        productEntity.setPrice(9000000d);
        productEntity.setDescription("Muscle car");
        productEntity.setTags(new ArrayList<>(Arrays.asList("car", "muscle", "american car")));
        productEntity.setCount(1);
        SatelliteEntity satelliteEntity = new SatelliteEntity();
        satelliteEntity.setName("Novosibirsk");
        satelliteEntity.setDescription("Siberia");
        productEntity.setSatellite(satelliteEntity);

        ProductRepository productRepo = new ProductRepository();
        productRepo.save(productEntity);
    }
}
