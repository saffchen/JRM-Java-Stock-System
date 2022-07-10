package saffchen.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import saffchen.checkvalidation.City;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private long id;

    @Column(name="title", nullable = false)
    @NotEmpty(message = "Название не может быть пустым!")
    @Length(max = 255, message = "Название не может быть длиннее 255 символов!")
    private String title;

    @Column(name="description")
    @NotEmpty(message = "Описание не может быть пустым!")
    @Length(max = 1024, message = "Описание не может быть длиннее 1024 символов!")
    private String description;

    @Column(name="price", nullable = false)
    @NotNull(message = "Цена не может отсутствовать!")
    @Positive(message = "Цена не может быть равна 0 или быть отрицательной!")
    private Double price;

    @Column(name="tags")
    @NotEmpty(message = "Тег не может быть пустым!")
    @Size(max = 20, message = "Количество тегов не может быть более 20!")
    @ElementCollection
    private List<String> tags;

    @Column(name="category", nullable = false)
    @NotEmpty(message = "Категории не могут быть пустым!")
    @Length(max = 50, message = "Категорий не может быть более 60!")
    private String category;

    @Column(name="count", nullable = false)
    @NotNull(message = "Количество не может быть пустым!")
    @PositiveOrZero(message = "Количество не может быть отрицательным!")
    private Integer count;

    @ManyToOne
    @JoinColumn(name="satelliteId", referencedColumnName = "id")
    @NotNull(message = "Название города не может быть пустым!")
    @JsonBackReference
    private SatelliteEntity satellite;

    public ProductEntity(Map<String, String> newFieldsMap) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id && title.equals(that.title) &&
                Objects.equals(description, that.description) &&
                price.equals(that.price) && Objects.equals(tags, that.tags) &&
                category.equals(that.category) && count.equals(that.count) && satellite.equals(that.satellite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, tags, category, count, satellite);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", tags=" + tags +
                ", category='" + category + '\'' +
                ", count=" + count +
                ", satellite=" + satellite +
                '}';
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