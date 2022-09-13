package saffchen.controller.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saffchen.dto.SatelliteDto;
import saffchen.entities.SatelliteEntity;
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

    static final String REST_URL = "/api/v1/admin/satellites";

    @PostMapping
    public ResponseEntity<SatelliteDto> create(@RequestBody SatelliteDto satellite) {
        SatelliteEntity satelliteEntity = mapper.satelliteDtoToSatelliteEntity(satellite);
        log.info("create satellite {}", satelliteEntity);
        checkNew(satelliteEntity);
        return ResponseEntity.ok(mapper.satelliteToSatelliteDto(
                service.create(satelliteEntity)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NoEntityException {
        log.info("delete satelliteId {}", id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody SatelliteDto satelliteDto, @PathVariable long id) {
        SatelliteEntity satelliteEntity = mapper.satelliteDtoToSatelliteEntity(satelliteDto);
        log.info("update satellite {}", satelliteEntity);
        assureIdConsistent(satelliteEntity, id);
        service.save(satelliteEntity);
    }
}
