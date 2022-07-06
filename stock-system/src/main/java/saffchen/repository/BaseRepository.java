package saffchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */
    @NoRepositoryBean
    public interface BaseRepository<T> extends JpaRepository<T, Long> {
    }
