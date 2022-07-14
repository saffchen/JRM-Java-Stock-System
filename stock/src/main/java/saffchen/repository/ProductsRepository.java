package saffchen.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.ProductEntity;

import java.util.List;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */
@Transactional(readOnly = true)
@Repository
public interface ProductsRepository extends BaseRepository<ProductEntity> {
    @Query(value = "select p from ProductEntity p where p.satellite.id = :satellite")
    List<ProductEntity> productCountBySatelliteId(@Param("satellite") Long satellite);
}