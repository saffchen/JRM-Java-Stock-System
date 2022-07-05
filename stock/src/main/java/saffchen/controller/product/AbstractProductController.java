package saffchen.controller.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import saffchen.entities.ProductEntity;
import saffchen.service.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * @author alex_jd on 6/24/22
 * @project JRM-Java-Stock-System
 */
@Slf4j
public class AbstractProductController {

    @Autowired
    protected ProductService service;

    public ResponseEntity<List<ProductEntity>> getAll() {
        log.info("get all products");
        return ResponseEntity.of(Optional.ofNullable(service.getAll()));
    }
}
