package saffchen.controller.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saffchen.product.Product;

import java.util.List;

/**
 * @author alex_jd on 6/24/22
 * @project JRM-Java-Stock-System
 */
@RestController
@RequestMapping(value = ProductController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ProductController extends AbstractProductController {

    static final String REST_URL = "/api/products";

    @Override
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        log.info("getAll");
        return super.getAll();
    }
}
