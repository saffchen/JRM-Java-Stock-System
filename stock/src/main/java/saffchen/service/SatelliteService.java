package saffchen.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saffchen.entities.SatelliteEntity;
import saffchen.exception.NoEntityException;
import saffchen.exception.SatelliteAlreadyExistException;
import saffchen.repository.SatellitesRepository;

import java.util.List;

import static java.util.Collections.emptyList;

@Slf4j
@Service
@RequiredArgsConstructor
public class SatelliteService {

    @Autowired
    private SatellitesRepository satelliteRepository;

    public List<SatelliteEntity> getAll() {
        try {
            return satelliteRepository.findAll();
        } catch (Exception e) {
            log.error("Objects are not found");
            return emptyList();
        }
    }

    public SatelliteEntity get(Long id) {
        return satelliteRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("Object with id " + id + "is not found"));
    }

    public SatelliteEntity create(SatelliteEntity satellite) {
        SatelliteEntity satelliteIsExist = satelliteRepository.findByName(satellite.getName())
                .orElse(null);
        if (satelliteIsExist == null) {
            return satelliteRepository.save(satellite);
        }
        throw new SatelliteAlreadyExistException("This satellite already has been exist.");
    }

    public void update(SatelliteEntity satellite) {
        satelliteRepository.save(satellite);
    }

    public void delete(Long id) {
        satelliteRepository.delete(id);
    }
}
