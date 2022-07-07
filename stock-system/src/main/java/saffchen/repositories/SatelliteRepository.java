package saffchen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import saffchen.entities.SatelliteEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SatelliteRepository extends JpaRepository<SatelliteEntity, Long> {
    Optional<SatelliteEntity> findByNameIgnoreCase(String name);

}
