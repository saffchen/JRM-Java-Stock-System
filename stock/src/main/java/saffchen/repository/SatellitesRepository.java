package saffchen.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.SatelliteEntity;

@Transactional(readOnly = true)
@Repository
public interface SatellitesRepository extends BaseRepository<SatelliteEntity>{

}
