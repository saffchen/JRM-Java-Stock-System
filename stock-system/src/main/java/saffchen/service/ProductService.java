package saffchen.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import saffchen.product.Product;

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

    public List<Product> getAll() {
        Product product = new Product("testTitle", "testDescription", 5000.0, List.of("tag1", "tag2"), "testCategory", 100, "Moscow");
        Product product1 = new Product("testTitle1", "testDescription1", 10000.0, List.of("tag3", "tag4"), "testCategory1", 200, "Moscow");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);
        return productList;
    }
}
