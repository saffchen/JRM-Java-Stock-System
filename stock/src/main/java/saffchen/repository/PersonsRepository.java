package saffchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.PersonEntity;
import saffchen.entities.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@EnableJpaRepositories
public interface PersonsRepository extends JpaRepository<PersonEntity, Integer> {
    @Query(value = "select p from PersonEntity p where p.email = :email")
    Optional<PersonEntity> findByEmail(@Param("email") String email);
}