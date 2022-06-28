package saffchen.controller.satellite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saffchen.entities.SatelliteEntity;
import saffchen.service.SatelliteService;

import java.util.List;

@RestController
@RequestMapping(value = SatelliteRestController.REST_URL)
public class SatelliteRestController {
    static final String REST_URL = "/api/satellites";
    private final SatelliteService satelliteService;

    @Autowired
    public SatelliteRestController(SatelliteService satelliteService){
        this.satelliteService = satelliteService;
    }

    @GetMapping()
    public List<SatelliteEntity> getSatellites(){
        return satelliteService.findAll();
    }

    @GetMapping(value = SatelliteRestController.REST_URL + "/{id}")
    public SatelliteEntity getSatellite(@PathVariable("id") Long id){
        return satelliteService.findOne(id);
    }
}