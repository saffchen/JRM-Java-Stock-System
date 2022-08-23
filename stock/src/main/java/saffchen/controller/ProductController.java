package saffchen.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saffchen.dto.ProductDto;
import saffchen.mapper.ProductMapper;
import saffchen.service.ProductService;

import java.util.List;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */

@AllArgsConstructor
@RestController
@RequestMapping(value = ProductController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    static final String REST_URL = "/api/v1/products";
    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productMapper.toProductsDtoList(productService.getAllProducts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productMapper.productToProductDto(productService.getProductById(id)));
    }

}