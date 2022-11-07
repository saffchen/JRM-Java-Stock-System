package saffchen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import saffchen.entities.StoreEntity;
import saffchen.error.NoEntityException;
import saffchen.repository.StoreRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StoreService {

    private StoreRepository storeRepository;

    public List<StoreEntity> getAll() {
        return storeRepository.findAll();
    }

    public StoreEntity get(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("Object with id " + id + " is not found"));
    }

    public StoreEntity getByName(String storeName) {
        return storeRepository.findByName(storeName).get();
    }

    public StoreEntity create(StoreEntity storeEntity) {
        return storeRepository.save(storeEntity);
    }

    public void save(StoreEntity storeEntity) {
        storeRepository.save(storeEntity);
    }

    public void delete(Long id) {
        storeRepository.deleteExisted(id);
    }
}
