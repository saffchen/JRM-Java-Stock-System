package saffchen.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="Satellite")
public class SatelliteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    @NonNull
    private String name;

    @Column(name="description")
    @NonNull
    private String description;

    @OneToMany(mappedBy = "satellite")
    private final List<ProductEntity> products = new ArrayList<>();

    public SatelliteEntity(@NonNull String name) {
        this.name = name;
        description = "";
    }

    @Override
    public String toString() {
        return "Satellite{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", products=" + products +
                '}';
    }
}