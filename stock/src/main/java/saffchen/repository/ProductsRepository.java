package saffchen.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.ProductEntity;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */
@Transactional(readOnly = true)
@Repository
public interface ProductsRepository extends BaseRepository<ProductEntity> {
}