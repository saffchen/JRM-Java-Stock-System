package saffchen.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saffchen.dto.ProductDto;
import saffchen.entities.ProductEntity;
import saffchen.mapper.ProductMapper;
import saffchen.service.ProductService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static saffchen.util.validation.ValidationUtil.assureIdConsistent;
import static saffchen.util.validation.ValidationUtil.checkNew;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */

@AllArgsConstructor
@RestController
@RequestMapping(value = ProductController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ProductController {
    static final String REST_URL = "/api/v1/satellites/{satelliteId}/products";

    private final ProductMapper mapper;
    private final ProductService service;

    // Get All for particular Satellite
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getBySatellite(@PathVariable long satelliteId) {
        log.info("getBySatellite for satelliteId={}", satelliteId);
        return ResponseEntity.ok(service.getBySatellite(satelliteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable Long satelliteId, @PathVariable Long id) {
        return ResponseEntity.ok(mapper.productToProductDto(service.get(satelliteId, id)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long satelliteId, @PathVariable Long id) {
        log.info("delete product id={} for satelliteId={}", id, satelliteId);
        service.delete(satelliteId, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductEntity> createWithLocation(@PathVariable Long satelliteId, @Valid @RequestBody ProductEntity product) {
        log.info("create {} for satelliteId={}", product, satelliteId);
        checkNew(product);
        ProductEntity created = service.save(satelliteId, product);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                          .path(REST_URL + "/{id}")
                                                          .buildAndExpand(satelliteId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long satelliteId, @Valid @RequestBody ProductEntity product, @PathVariable Long id) {
        log.info("update product with id={} for satelliteId={}", id, satelliteId);
        assureIdConsistent(product, id);
        service.update(satelliteId, product, id);
    }
}