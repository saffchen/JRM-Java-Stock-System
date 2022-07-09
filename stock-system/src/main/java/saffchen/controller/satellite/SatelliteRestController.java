package saffchen.controller.satellite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saffchen.dto.SatelliteDTO;
import saffchen.entities.SatelliteEntity;
import saffchen.service.SatelliteService;

import java.util.List;

@RestController
@RequestMapping("/api/satellites")
public class SatelliteRestController {

    private final SatelliteService satelliteService;

    @Autowired
    public SatelliteRestController(SatelliteService satelliteService){
        this.satelliteService = satelliteService;
    }

    @GetMapping("/all")
    public List<SatelliteDTO> getAllSatellitesWithProductCount(){
        return satelliteService.findAll();
    }

    @GetMapping("/id/{id}")
    public SatelliteEntity getSatellite(@PathVariable("id") Long id){

        return satelliteService.findById(id);
    }

    @GetMapping("/name/{name}")
    public SatelliteEntity getSatellite(@PathVariable("name") String name){
        return satelliteService.findByNameIgnoreCase(name);
    }
}
