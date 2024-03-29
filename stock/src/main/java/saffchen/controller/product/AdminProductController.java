package saffchen.controller.product;

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
@RequestMapping(value = AdminProductController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminProductController extends AbstractProductController {

    static final String REST_URL = "/api/v1/admin/stores/{storeId}/products";

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long storeId, @PathVariable Long id) {
        log.info("delete product id={} for storeId={}", id, storeId);
        service.delete(storeId, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductEntity> createWithLocation(@PathVariable Long storeId, @Valid @RequestBody ProductEntity product) {
        log.info("create new product for storeId={}", storeId);
        checkNew(product);
        ProductEntity created = service.save(storeId, product);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(storeId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long storeId, @Valid @RequestBody ProductEntity product, @PathVariable Long id) {
        log.info("update product with id={} for storeId={}", id, storeId);
        assureIdConsistent(product, id);
        service.update(storeId, product, id);
    }
}
