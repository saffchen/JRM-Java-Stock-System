package saffchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saffchen.entities.UserEntity;
import java.util.Optional;

@Repository
@Transactional
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "select p from UserEntity p where p.email = :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);
}