package saffchen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saffchen.dto.SatelliteDTO;
import saffchen.entities.ProductEntity;
import saffchen.entities.SatelliteEntity;
import saffchen.repositories.SatelliteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SatelliteService {
    @PersistenceContext
    private EntityManager em;
    private final SatelliteRepository satelliteRepository;

    @Autowired
    public SatelliteService(SatelliteRepository satelliteRepository) {

        this.satelliteRepository = satelliteRepository;
    }

    public List<SatelliteDTO> findAll(){
        List<SatelliteDTO> sdtos =  satelliteRepository.findAllSatellitesWithProductCount();
        for (SatelliteDTO sdto: sdtos) {
            System.out.println(sdto.getId() + " : " + sdto.getName() + " : " + sdto.getDescription() + " : " + sdto.getCount());
        }
        return sdtos;
    }
    private SatelliteDTO convertToSatelliteDTO(SatelliteEntity satelliteEntity){
        SatelliteDTO satelliteDTO = new SatelliteDTO();
        //satelliteDTO.setId(Long.valueOf(satelliteEntity.getId()));
        satelliteDTO.setName(satelliteEntity.getName());
        //satelliteDTO.setDescription(satelliteDTO.getDescription());
        return satelliteDTO;
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
