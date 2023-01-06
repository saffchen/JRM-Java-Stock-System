package saffchen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import saffchen.entities.ProductEntity;
import saffchen.error.DataConflictException;
import saffchen.repository.ProductRepository;
import saffchen.repository.StoreRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final StoreRepository storeRepository;

    public ProductEntity get(Long storeId, Long id) {
        return productRepository.get(storeId, id)
                .orElseThrow(
                        () -> new DataConflictException("Object with id " + id + " for store id=" + storeId + " is not found.")
                );
    }

    public void delete(Long storeId, Long id) {
        ProductEntity product = productRepository.checkBelong(storeId, id);
        productRepository.delete(product);
    }

    public List<ProductEntity> getByStore(Long storeId) {
        return productRepository.getBySatellite(storeId);
    }

    public ProductEntity save(Long storeId, ProductEntity product) {
        product.setStore(storeRepository.getById(storeId));
        return productRepository.save(product);
    }

    public void update(Long storeId, ProductEntity product, Long id) {
        productRepository.checkBelong(storeId, id);
        save(storeId, product);
    }

    public List<ProductEntity> getProductsBySatelliteId(Long id) throws NoEntityException{
        try {
            return productRepository.productCountBySatelliteId(id);
        } catch (Exception e){
            log.error("Object is not found");
            return emptyList();
        }
    }
}
