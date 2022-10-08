package saffchen.controller.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saffchen.dto.StoreDto;
import saffchen.entities.StoreEntity;

import java.util.List;


@RestController
@RequestMapping(value = StoreController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class StoreController extends AbstractStoreController {
    static final String REST_URL = "/api/v1/stores";

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAll() {
        log.info("get all Stores");
        return ResponseEntity.ok(service.getAll().stream()
                                        .map(x -> {
                                            StoreDto storeDto = mapper.storeToStoreDto(x);
                                            storeDto.setCount(x.getProductsSize());
                                            return storeDto;
                                        })
                                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> get(@PathVariable Long id) {
        log.info("get store by id {}", id);
        StoreEntity storeEntity = service.get(id);
        StoreDto storeDto = mapper.storeToStoreDto(storeEntity);
        storeDto.setCount(storeEntity.getProductsSize());
        return ResponseEntity.ok(storeDto);
    }
}