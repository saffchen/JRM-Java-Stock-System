package saffchen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import saffchen.dto.SatelliteDTO;
import saffchen.entities.SatelliteEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SatelliteRepository extends JpaRepository<SatelliteEntity, Long> {
    Optional<SatelliteEntity> findByNameIgnoreCase(String name);

    @Query("SELECT new saffchen.dto.SatelliteDTO(se.id, se.name, se.description, COUNT(p.id)) " +
            "FROM SatelliteEntity se " +
            "JOIN se.products p GROUP BY se.id")
    List<SatelliteDTO> findAllSatellitesWithProductCount();
}
