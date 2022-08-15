package saffchen.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saffchen.dto.SatelliteDto;
import saffchen.entities.SatelliteEntity;
import saffchen.exception.NoEntityException;
import saffchen.mapper.SatelliteMapper;
import saffchen.service.SatelliteService;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = SatelliteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SatelliteController {
    static final String REST_URL = "/api/satellites";
    private final SatelliteMapper satelliteMapper;
    private final SatelliteService satelliteService;

    @GetMapping
    public ResponseEntity<List<SatelliteDto>> getAll() {
        return ResponseEntity.ok(satelliteService.getAll().stream()
                .map(x -> {
                    SatelliteDto satelliteDto = satelliteMapper.satelliteToSatelliteDto(x);
                    satelliteDto.setCount(x.getProductsSize());
                    return satelliteDto;
                })
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SatelliteDto> get(@PathVariable Long id) throws NoEntityException {
        SatelliteEntity satelliteEntity = satelliteService.get(id);
        SatelliteDto satelliteDto = satelliteMapper.satelliteToSatelliteDto(satelliteEntity);
        satelliteDto.setCount(satelliteEntity.getProductsSize());
        return ResponseEntity.ok(satelliteDto);
    }

    @PostMapping
    public ResponseEntity<SatelliteDto> create(@RequestBody SatelliteDto satellite) {
        return ResponseEntity.ok(satelliteMapper.satelliteToSatelliteDto(
                satelliteService.create(
                        satelliteMapper.satelliteDtoToSatelliteEntity(satellite))));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NoEntityException {
        satelliteService.delete(satelliteService.get(id).getId());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody SatelliteDto satelliteDto, @PathVariable long id) {
        SatelliteEntity satellite = satelliteMapper.satelliteDtoToSatelliteEntity(satelliteDto);
        satellite.setId(id);
        satelliteService.update(satellite);
    }
}