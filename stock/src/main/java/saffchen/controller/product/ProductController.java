package saffchen.controller.product;

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


@RestController
@RequestMapping(value = ProductController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
@Slf4j
public class ProductController extends AbstractProductController {
    static final String REST_URL = "/api/v1/satellites/{satelliteId}/products";

    /*private final ProductMapper mapper;
    private final ProductService service;*/

    // Get All for particular Satellite
    @GetMapping
    public ResponseEntity<List<ProductDto>> getBySatellite(@PathVariable long satelliteId) {
        log.info("get products by satellite for satelliteId={}", satelliteId);
        return ResponseEntity.ok(mapper.productToProductDtoList(service.getBySatellite(satelliteId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable Long satelliteId, @PathVariable Long id) {
        log.info("get product with id={} for satelliteId={}", id, satelliteId);
        return ResponseEntity.ok(mapper.productToProductDto(service.get(satelliteId, id)));
    }
}