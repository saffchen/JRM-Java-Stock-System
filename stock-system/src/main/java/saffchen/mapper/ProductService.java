package saffchen.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saffchen.entities.ProductEntity;
import saffchen.repository.ProductsRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductsRepository productRepository;

    public List<ProductEntity> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            log.error("Object is not found");
            return Collections.emptyList();
        }
    }

    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}
