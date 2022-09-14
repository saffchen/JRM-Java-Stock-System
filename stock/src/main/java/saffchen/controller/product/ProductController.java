package saffchen.controller.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saffchen.dto.ProductDto;

import java.util.List;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */


@RestController
@RequestMapping(value = ProductController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
@Slf4j
public class ProductController extends AbstractProductController {
    static final String REST_URL = "/api/v1/stores/{storeId}/products";

    /*private final ProductMapper mapper;
    private final ProductService service;*/

    // Get All for particular Satellite
    @GetMapping
    public ResponseEntity<List<ProductDto>> getBySatellite(@PathVariable long storeId) {
        log.info("get products by store for storeId={}", storeId);
        return ResponseEntity.ok(mapper.productToProductDtoList(service.getByStore(storeId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable Long storeId, @PathVariable Long id) {
        log.info("get product with id={} for storeId={}", id, storeId);
        return ResponseEntity.ok(mapper.productToProductDto(service.get(storeId, id)));
    }
}