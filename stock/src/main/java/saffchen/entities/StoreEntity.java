package saffchen.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "uk_satellite")})
public class StoreEntity extends NamedEntity {

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<ProductEntity> products;

    public StoreEntity(String store) {
    }

    public int getProductsSize() {
        return products.size();
    }
}