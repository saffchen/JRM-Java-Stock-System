package saffchen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

/**
 * @author alex_jd on 8/16/22
 * @project JRM-Java-Stock-System
 */

public interface HasId {
    Long getId();

    void setId(Long id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }

    // doesn't work for hibernate lazy proxy
    default Long id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}

