package saffchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} u WHERE u.id=:id")
    int delete(@Param("id") Long id);
}