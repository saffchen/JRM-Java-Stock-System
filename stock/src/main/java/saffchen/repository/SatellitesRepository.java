package saffchen.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.SatelliteEntity;

import java.util.Optional;

@Transactional
@Repository
public interface SatellitesRepository extends BaseRepository<SatelliteEntity> {
    Optional<SatelliteEntity> findByName(String name);
}
