package saffchen.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import saffchen.entities.ProductEntity;
import saffchen.entities.SatelliteEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alex_jd on 6/24/22
 * @project JRM-Java-Stock-System
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    public List<ProductEntity> getAll() {
        ProductEntity product = new ProductEntity("testTitle", "testDescription", 5000.0, List.of("tag1", "tag2"), "testCategory", 100, new SatelliteEntity("Moscow"));
        ProductEntity product1 = new ProductEntity("testTitle1", "testDescription1", 10000.0, List.of("tag3", "tag4"), "testCategory1", 200, new SatelliteEntity("Moscow"));
        List<ProductEntity> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);
        return productList;
    }
}
