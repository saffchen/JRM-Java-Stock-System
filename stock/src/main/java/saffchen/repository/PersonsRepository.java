package saffchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.PersonEntity;

import java.util.Optional;

@Repository
@Transactional
@EnableJpaRepositories
public interface PersonsRepository extends JpaRepository<PersonEntity, Integer> {
    Optional<PersonEntity> findByUsername(String userName);
}