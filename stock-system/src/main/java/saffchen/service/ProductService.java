package saffchen.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saffchen.entities.ProductEntity;
import saffchen.exception.NoEntityException;
import saffchen.repository.ProductsRepository;

import java.util.List;

import static java.util.Collections.emptyList;

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
            return emptyList();
        }
    }

    public ProductEntity getProductById(Long id) throws NoEntityException {
        return productRepository.findById(id).orElseThrow(() -> new NoEntityException("Object with id " + id + " is not found"));
    }
}
