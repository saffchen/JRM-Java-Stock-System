package saffchen.repository;

import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.ProductEntity;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */
@Transactional(readOnly = true)
public interface ProductsRepository extends BaseRepository<ProductEntity> {
}