package saffchen.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Satellite")
public class SatelliteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "satellite")
    @JsonManagedReference
    private List<ProductEntity> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SatelliteEntity that = (SatelliteEntity) o;
        return id.equals(that.id) && name.equals(that.name) && Objects.equals(description, that.description) && products.equals(that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, products);
    }

    @Override
    public String toString() {
        return "SatelliteEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", products=" + products +
                '}';
    }
}