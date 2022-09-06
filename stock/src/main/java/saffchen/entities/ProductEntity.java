package saffchen.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = {"satellite_id", "name"}, name = "uk_product")})
public class ProductEntity extends NamedEntity {

    @Column(name = "description")
    @NotEmpty(message = "Описание не может быть пустым!")
    @Length(max = 1024, message = "Описание не может быть длиннее 1024 символов!")
    @NonNull
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Цена не может отсутствовать!")
    @Positive(message = "Цена не может быть равна 0 или быть отрицательной!")
    @NonNull
    private Double price;

    @CollectionTable(name = "product_tags",
                    joinColumns = @JoinColumn(name = "product_id"),
                    uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "tags"}, name = "uk_product_tags"))
    @Column(name = "tags")
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotEmpty(message = "Тег не может быть пустым!")
    @Size(max = 20, message = "Количество тегов не может быть более 20!")
    @NonNull
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags;

    @Column(name = "category", nullable = false)
    @NotEmpty(message = "Категории не могут быть пустым!")
    @Length(max = 50, message = "Категорий не может быть более 60!")
    @NonNull
    private String category;

    @Column(name = "count", nullable = false)
    @NotNull(message = "Количество не может быть пустым!")
    @PositiveOrZero(message = "Количество не может быть отрицательным!")
    @NonNull
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "satelliteId", referencedColumnName = "id")
    @NonNull
    @JsonBackReference
    private SatelliteEntity satellite;

    public ProductEntity(Map<String, String> fieldsMap) {
        id = Long.parseLong(fieldsMap.get("id"));
        name = fieldsMap.get("title");
        description = fieldsMap.get("description");
        price = Double.parseDouble(fieldsMap.get("price"));
        tags = Arrays.asList(fieldsMap.get("tags").split(","));
        category = fieldsMap.get("category");
        count = Integer.parseInt(fieldsMap.get("count"));
    }

    @Override
    public String toString() {
        return name + ", " + description + ", " + price + ", " + tags + ", " + category + ", " + count + ", " + satellite.toString();
    }

    public String showInfo() {
        return "Product{" +
                "id=" + id +
                ", title='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", tags=" + tags +
                ", category='" + category + '\'' +
                ", count=" + count +
                ", satellite='" + satellite.toString() + '\'' +
                '}';
    }
}