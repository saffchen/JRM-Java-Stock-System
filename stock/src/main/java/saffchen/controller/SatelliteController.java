package saffchen.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saffchen.dto.SatelliteDto;
import saffchen.entities.SatelliteEntity;
import saffchen.exception.NoEntityException;
import saffchen.mapper.SatelliteMapper;
import saffchen.service.SatelliteService;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping(value = SatelliteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SatelliteController {
    static final String REST_URL = "/api/satellites";
    private final SatelliteMapper satelliteMapper;
    private final SatelliteService satelliteService;

    @GetMapping
    public ResponseEntity<List<SatelliteDto>> getAllSatellites() {
        List<SatelliteDto> satelliteDtoList = satelliteMapper.toSatellitesDtoList(satelliteService.getAllSatellites());
        for(SatelliteDto satelliteDto : satelliteDtoList)
            satelliteDto.setCount(satelliteService.getProductCountBySatelliteId(satelliteDto.getId()));
        return ResponseEntity.ok(satelliteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SatelliteDto> getSatelliteById(@PathVariable Long id) throws NoEntityException {
        SatelliteDto satelliteDto = satelliteMapper.satelliteToSatelliteDto(satelliteService.getSatelliteById(id));
        satelliteDto.setCount(satelliteService.getProductCountBySatelliteId(id));
        return ResponseEntity.ok(satelliteDto);
    }

    @PostMapping
    public ResponseEntity<SatelliteEntity> saveNewSatellite(@RequestBody SatelliteEntity satellite){
       return ResponseEntity.ok(satelliteService.saveNewSatellite(satellite));
    }
}