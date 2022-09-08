package saffchen.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saffchen.entities.SatelliteEntity;
import saffchen.error.NoEntityException;
import saffchen.repository.SatellitesRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class SatelliteService {

    private SatellitesRepository satelliteRepository;

    public List<SatelliteEntity> getAll() {
        return satelliteRepository.findAll();
    }

    public SatelliteEntity get(Long id) {
        return satelliteRepository.findById(id)
                                  .orElseThrow(() -> new NoEntityException("Object with id " + id + " is not found"));
    }

    public SatelliteEntity getByName(String satelliteName) {
        return satelliteRepository.findByName(satelliteName).get();
    }

    public SatelliteEntity create(SatelliteEntity satellite) {
        return satelliteRepository.save(satellite);
    }

    public void save(SatelliteEntity satellite) {
        satelliteRepository.save(satellite);
    }

    public void delete(Long id) {
        satelliteRepository.deleteExisted(id);
    }
}
