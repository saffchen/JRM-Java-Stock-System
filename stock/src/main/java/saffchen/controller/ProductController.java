package saffchen.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saffchen.dto.ProductDto;
import saffchen.exception.ErrorResponse;
import saffchen.exception.GlobalExceptionHandler;
import saffchen.exception.SatelliteAlreadyExistException;
import saffchen.mapper.ProductMapper;
import saffchen.exception.NoEntityException;
import saffchen.service.ProductService;

import java.util.List;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */

@AllArgsConstructor
@RestController
@RequestMapping(value = ProductController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController extends GlobalExceptionHandler {
    static final String REST_URL = "/api/products";
    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productMapper.toProductsDtoList(productService.getAllProducts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) throws NoEntityException {
        return ResponseEntity.ok(productMapper.productToProductDto(productService.getProductById(id)));
    }

}