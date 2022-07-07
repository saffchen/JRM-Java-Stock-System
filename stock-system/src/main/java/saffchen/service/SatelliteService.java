package saffchen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.ProductEntity;
import saffchen.entities.SatelliteEntity;
import saffchen.repositories.SatelliteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public List<SatelliteEntity> findAll(){
        List<SatelliteEntity> lse = em.createQuery(
                        "SELECT se.id, se.name, se.description, COUNT(p.id) " +
                                "FROM SatelliteEntity se " +
                                "JOIN se.products p " +
                                "GROUP BY se.id")
                .getResultList();
        return lse;
        //return satelliteRepository.findAllSatellites();
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
