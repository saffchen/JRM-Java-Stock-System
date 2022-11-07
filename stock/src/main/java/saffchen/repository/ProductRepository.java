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
public interface ProductRepository extends BaseRepository<ProductEntity> {
    @Query("SELECT pr FROM ProductEntity pr WHERE pr.id=:id AND pr.store.id=:storeId")
    Optional<ProductEntity> get(Long storeId, Long id);

    @Query("SELECT pr FROM ProductEntity pr WHERE pr.store.id=:storeId ORDER BY pr.name ASC")
    List<ProductEntity> getBySatellite(Long storeId);

    default ProductEntity checkBelong(Long storeId, Long id) {
        return get(storeId, id).orElseThrow(
                () -> new DataConflictException("Product id=" + id + " doesn't belong to Store id=" + storeId)
        );
    }

}