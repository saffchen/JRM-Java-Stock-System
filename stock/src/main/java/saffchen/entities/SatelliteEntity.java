package saffchen.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Satellite")
public class SatelliteEntity {
    @Id
    @SequenceGenerator(name = "satellite_id_sequence",
            sequenceName = "satellite_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "satellite_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "satellite")
    @JsonManagedReference
    private List<ProductEntity> products;

    public SatelliteEntity(String satellite) {
    }
}