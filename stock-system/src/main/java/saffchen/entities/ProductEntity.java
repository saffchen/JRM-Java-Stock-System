package saffchen.entities;

import lombok.*;
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
@RequiredArgsConstructor
@Table(name="Product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private long id;

    @Column(name="title", nullable = false)
    @NotEmpty(message = "Название не может быть пустым!")
    @Length(max = 255, message = "Название не может быть длиннее 255 символов!")
    @NonNull
    private String title;

    @Column(name="description")
    @NotEmpty(message = "Описание не может быть пустым!")
    @Length(max = 1024, message = "Описание не может быть длиннее 1024 символов!")
    @NonNull
    private String description;

    @Column(name="price", nullable = false)
    @NotNull(message = "Цена не может отсутствовать!")
    @Positive(message = "Цена не может быть равна 0 или быть отрицательной!")
    @NonNull
    private Double price;

    @Column(name="tags")
    @NotEmpty(message = "Тег не может быть пустым!")
    @Size(max = 20, message = "Количество тегов не может быть более 20!")
    @NonNull
    @ElementCollection
    private List<String> tags;

    @Column(name="category", nullable = false)
    @NotEmpty(message = "Категории не могут быть пустым!")
    @Length(max = 50, message = "Категорий не может быть более 60!")
    @NonNull
    private String category;

    @Column(name="count", nullable = false)
    @NotNull(message = "Количество не может быть пустым!")
    @PositiveOrZero(message = "Количество не может быть отрицательным!")
    @NonNull
    private Integer count;

    @ManyToOne
    @JoinColumn(name="satelliteId", referencedColumnName = "id")
    @NotNull(message = "Название города не может быть пустым!")
    @NonNull
    private SatelliteEntity satellite;

    public ProductEntity(Map<String, String> fieldsMap) {
        id = Long.parseLong(fieldsMap.get("id"));
        title = fieldsMap.get("title");
        description = fieldsMap.get("description");
        price = Double.parseDouble(fieldsMap.get("price"));
        tags = Arrays.asList(fieldsMap.get("tags").split(","));
        category = fieldsMap.get("category");
        count = Integer.parseInt(fieldsMap.get("count"));
    }

    @Override
    public String toString() {
        return title + ", " + description + ", " + price + ", " + tags + ", " + category + ", " + count + ", " + satellite.toString();
    }

    public String showInfo() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", tags=" + tags +
                ", category='" + category + '\'' +
                ", count=" + count +
                ", satellite='" + satellite.toString() + '\'' +
                '}';
    }
}