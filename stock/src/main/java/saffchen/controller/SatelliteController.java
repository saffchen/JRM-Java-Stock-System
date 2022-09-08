package saffchen.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saffchen.dto.SatelliteDto;
import saffchen.entities.SatelliteEntity;
import saffchen.error.NoEntityException;
import saffchen.mapper.SatelliteMapper;
import saffchen.service.SatelliteService;

import javax.validation.Valid;
import java.util.List;

import static saffchen.util.validation.ValidationUtil.assureIdConsistent;
import static saffchen.util.validation.ValidationUtil.checkNew;

@AllArgsConstructor
@RestController
@RequestMapping(value = SatelliteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class SatelliteController {
    static final String REST_URL = "/api/v1/satellites";
    private final SatelliteMapper mapper;
    private final SatelliteService service;

    @GetMapping
    public ResponseEntity<List<SatelliteDto>> getAll() {
        log.info("get all Satellites");
        return ResponseEntity.ok(service.getAll().stream()
                                        .map(x -> {
                                        SatelliteDto satelliteDto = mapper.satelliteToSatelliteDto(x);
                                        satelliteDto.setCount(x.getProductsSize());
                                        return satelliteDto;
                                    })
                                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SatelliteDto> get(@PathVariable Long id) {
        log.info("get satellite by id {}", id);
        SatelliteEntity satelliteEntity = service.get(id);
        SatelliteDto satelliteDto = mapper.satelliteToSatelliteDto(satelliteEntity);
        satelliteDto.setCount(satelliteEntity.getProductsSize());
        return ResponseEntity.ok(satelliteDto);
    }

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