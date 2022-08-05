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
        List<SatelliteDto> satelliteDtoList = satelliteMapper.toSatellitesDtoList(satelliteService.getAll());
        for (SatelliteDto satelliteDto : satelliteDtoList)
            satelliteDto.setCount(satelliteService.getProductCountBySatelliteId(satelliteDto.getId()));
        return ResponseEntity.ok(satelliteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SatelliteDto> get(@PathVariable Long id) throws NoEntityException {
        SatelliteDto satelliteDto = satelliteMapper.satelliteToSatelliteDto(satelliteService.get(id));
        satelliteDto.setCount(satelliteService.getProductCountBySatelliteId(id));
        return ResponseEntity.ok(satelliteDto);
    }

    @PostMapping
    public ResponseEntity<SatelliteDto> create(@RequestBody SatelliteDto satellite) {
        return ResponseEntity.ok(satelliteMapper.satelliteToSatelliteDto(
                satelliteService.saveNewSatellite(
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