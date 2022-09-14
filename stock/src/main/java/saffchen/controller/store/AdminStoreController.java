package saffchen.controller.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saffchen.dto.StoreDto;
import saffchen.entities.StoreEntity;
import saffchen.error.NoEntityException;

import javax.validation.Valid;

import static saffchen.util.validation.ValidationUtil.assureIdConsistent;
import static saffchen.util.validation.ValidationUtil.checkNew;

/**
 * @author alex_jd on 9/13/22
 * @project JRM-Java-Stock-System
 */

@RestController
@RequestMapping(value = StoreController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminStoreController extends AbstractStoreController {

    static final String REST_URL = "/api/v1/admin/stores";

    @PostMapping
    public ResponseEntity<StoreDto> create(@RequestBody StoreDto satellite) {
        StoreEntity storeEntity = mapper.storeDtoToStoreEntity(satellite);
        log.info("create store {}", storeEntity);
        checkNew(storeEntity);
        return ResponseEntity.ok(mapper.storeToStoreDto(
                service.create(storeEntity)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NoEntityException {
        log.info("delete storeId {}", id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody StoreDto satelliteDto, @PathVariable long id) {
        StoreEntity storeEntity = mapper.storeDtoToStoreEntity(satelliteDto);
        log.info("update store {}", storeEntity);
        assureIdConsistent(storeEntity, id);
        service.save(storeEntity);
    }
}
