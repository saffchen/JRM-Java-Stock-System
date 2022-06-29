package saffchen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saffchen.entities.SatelliteEntity;

import java.util.Optional;

@Repository
public interface SatelliteRepository extends JpaRepository<SatelliteEntity, Long> {
    Optional<SatelliteEntity> findByNameIgnoreCase(String name);
}
