package saffchen.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saffchen.entities.ProductEntity;
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
    static final String REST_URL = "/api/v1/products";
    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}/satellite/{satelliteId}")
    public ResponseEntity<ProductEntity> get(@PathVariable Long satelliteId, @PathVariable Long id) {
        return ResponseEntity.ok(service.get(satelliteId, id));
    }

    @DeleteMapping("/{id}/satellite/{satelliteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long satelliteId, @PathVariable Long id) {
        log.info("delete product id={} for satelliteId={}", id, satelliteId);
        service.delete(satelliteId, id);
    }

    @GetMapping("/satellite/{satelliteId}")
    public ResponseEntity<List<ProductEntity>> getBySatellite(@PathVariable long satelliteId) {
        log.info("getBySatellite for satelliteId={}", satelliteId);
        return ResponseEntity.ok(service.getBySatellite(satelliteId));
    }

    @PostMapping(value = "/satellite/{satelliteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductEntity> createWithLocation(@PathVariable Long satelliteId, @Valid @RequestBody ProductEntity product) {
        log.info("create {} for satelliteId={}", product, satelliteId);
        checkNew(product);
        ProductEntity created = service.save(satelliteId, product);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                          .path(REST_URL + "/{id}/satellite/{satelliteId}")
                                                          .buildAndExpand(satelliteId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}/satellite/{satelliteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long satelliteId, @Valid @RequestBody ProductEntity product, @PathVariable Long id) {
        log.info("update {} for satelliteId={}, id={}", product, satelliteId, id);
        assureIdConsistent(product, id);
        service.update(satelliteId, product, id);
    }
}