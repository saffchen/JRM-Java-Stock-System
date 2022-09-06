package saffchen.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.ProductEntity;
import saffchen.error.DataConflictException;

import java.util.List;
import java.util.Optional;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */
@Transactional(readOnly = true)
@Repository
public interface ProductsRepository extends BaseRepository<ProductEntity> {
    @Query(value = "select p from ProductEntity p where p.satellite.id = :satellite")
    List<ProductEntity> productCountBySatelliteId(@Param("satellite") Long satellite);

    @Query("SELECT pr FROM ProductEntity pr WHERE pr.id=:id AND pr.satellite.id=:satelliteId")
    Optional<ProductEntity> get(Long satelliteId,Long id);

    @Query("SELECT pr FROM ProductEntity pr WHERE pr.satellite.id=:satelliteId ORDER BY pr.name ASC")
    List<ProductEntity> getBySatellite(Long satelliteId);

    default ProductEntity checkBelong(Long satelliteId, Long id) {
        return get(satelliteId, id).orElseThrow(
                () -> new DataConflictException("Product id=" + id + " doesn't belong to Restaurant id=" + satelliteId)
        );
    }

}