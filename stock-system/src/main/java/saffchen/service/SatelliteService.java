package saffchen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.SatelliteEntity;
import saffchen.repositories.SatelliteRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SatelliteService {
    private final SatelliteRepository satelliteRepository;

    @Autowired
    public SatelliteService(SatelliteRepository satelliteRepository) {

        this.satelliteRepository = satelliteRepository;
    }

    public List<SatelliteEntity> findAll(){
        return satelliteRepository.findAll();
    }

    public SatelliteEntity findById(Long id){
        Long convertedId = Long.valueOf(id);
        Optional<SatelliteEntity> foundSatellite = satelliteRepository.findById(convertedId);
        return foundSatellite.orElse(null);
    }

    public SatelliteEntity findByNameIgnoreCase(String name){
        Optional<SatelliteEntity> foundSatellite = satelliteRepository.findByNameIgnoreCase(name);
        return foundSatellite.orElse(null);
    }
}
