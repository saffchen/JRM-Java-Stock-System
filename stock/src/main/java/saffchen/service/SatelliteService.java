package saffchen.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saffchen.entities.SatelliteEntity;
import saffchen.exception.NoEntityException;
import saffchen.exception.SatelliteAlreadyExistException;
import saffchen.repository.ProductsRepository;
import saffchen.repository.SatellitesRepository;

import java.util.List;

import static java.util.Collections.emptyList;

@Slf4j
@Service
@RequiredArgsConstructor
public class SatelliteService {

    @Autowired
    private SatellitesRepository satelliteRepository;
    @Autowired
    private ProductsRepository productRepository;

    public List<SatelliteEntity> getAllSatellites() {
        try {
            return satelliteRepository.findAll();
        } catch (Exception e) {
            log.error("Objects are not found");
            return emptyList();
        }
    }

    public SatelliteEntity getSatelliteById(Long id) throws NoEntityException {
        return satelliteRepository.findById(id).orElseThrow(() -> new NoEntityException("Object with id " + id + "is not found"));
    }

    public Long getProductCountBySatelliteId(Long id){
        return Long.valueOf(productRepository.productCountBySatelliteId(id).size());
    }

    public SatelliteEntity saveNewSatellite(SatelliteEntity satellite){
        SatelliteEntity satelliteIsExist = satelliteRepository.findById(satellite.getId())
                .orElse(null);
        if (satelliteIsExist == null) {
            return satelliteRepository.save(satellite);
        }
        else throw new SatelliteAlreadyExistException("You cannot specify the ID field yourself. Please drop the ID field and try again.");
  }
}