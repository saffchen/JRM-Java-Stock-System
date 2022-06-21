package saffchen.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import saffchen.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Satellite")
public class SatelliteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Name", unique = true, nullable = false)
    private String name;

    @Column(name="Description")
    private String description;

    @OneToMany(mappedBy = "satellite")
    private List<Product> products;
}