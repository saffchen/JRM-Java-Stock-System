package saffchen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import saffchen.entities.ProductEntity;
import saffchen.error.DataConflictException;
import saffchen.repository.ProductsRepository;
import saffchen.repository.SatellitesRepository;

import java.util.List;

import static java.util.Collections.emptyList;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    //@Autowired
    private final ProductsRepository productsRepository;

    private final SatellitesRepository satellitesRepository;

    public List<ProductEntity> getAll() {
        try {
            return productsRepository.findAll();
        } catch (Exception e) {
            log.error("Object is not found");
            return emptyList();
        }
    }

    public ProductEntity get(Long satelliteId, Long id) {
        return productsRepository.get(satelliteId, id)
                                 .orElseThrow(
                                         () -> new DataConflictException("Object with id " + id + " for satellite id=" + satelliteId + " is not found.")
                                 );
    }

    public void delete(Long satelliteId, Long id) {
        ProductEntity product = productsRepository.checkBelong(satelliteId, id);
        productsRepository.delete(product);
    }

    public List<ProductEntity> getBySatellite(Long satelliteId) {
        return productsRepository.getBySatellite(satelliteId);
    }

    public ProductEntity save(Long satelliteId, ProductEntity product) {
        product.setSatellite(satellitesRepository.getById(satelliteId));
        return productsRepository.save(product);
    }

    public void update(Long satelliteId, ProductEntity product, Long id) {
        productsRepository.checkBelong(satelliteId, id);
        save(satelliteId, product);
    }
}
