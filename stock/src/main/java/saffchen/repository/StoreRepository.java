package saffchen.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.StoreEntity;

import java.util.Optional;

@Transactional
@Repository
public interface StoreRepository extends BaseRepository<StoreEntity> {
    Optional<StoreEntity> findByName(String name);
}
