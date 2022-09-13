package saffchen.controller.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import saffchen.entities.ProductEntity;

import javax.validation.Valid;
import java.net.URI;

import static saffchen.util.validation.ValidationUtil.assureIdConsistent;
import static saffchen.util.validation.ValidationUtil.checkNew;

/**
 * @author alex_jd on 9/13/22
 * @project JRM-Java-Stock-System
 */

@RestController
@RequestMapping(value = ProductController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminProductController extends AbstractProductController {

    static final String REST_URL = "/api/v1/admin/satellites/{satelliteId}/products";

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long satelliteId, @PathVariable Long id) {
        log.info("delete product id={} for satelliteId={}", id, satelliteId);
        service.delete(satelliteId, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductEntity> createWithLocation(@PathVariable Long satelliteId, @Valid @RequestBody ProductEntity product) {
        log.info("create new product for satelliteId={}", satelliteId);
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
