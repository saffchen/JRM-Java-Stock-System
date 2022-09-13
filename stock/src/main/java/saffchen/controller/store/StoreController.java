package saffchen.controller.store;

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


@RestController
@RequestMapping(value = StoreController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
@Slf4j
public class StoreController extends AbstractStoreController {
    static final String REST_URL = "/api/v1/satellites";
    /*private final SatelliteMapper mapper;
    private final SatelliteService service;*/

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
}