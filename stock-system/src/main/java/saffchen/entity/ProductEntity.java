package saffchen.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import saffchen.checkvalidation.City;

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
@Table(name="Product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id", nullable = false, unique = true)
    private long id;

    @Column(name="Title", nullable = false)
    @NotEmpty(message = "Название не может быть пустым!")
    @Length(max = 255, message = "Название не может быть длиннее 255 символов!")
    private String title;

    @Column(name="Description")
    @NotEmpty(message = "Описание не может быть пустым!")
    @Length(max = 1024, message = "Описание не может быть длиннее 1024 символов!")
    private String description;

    @Column(name="Price", nullable = false)
    @NotNull(message = "Цена не может отсутствовать!")
    @Positive(message = "Цена не может быть равна 0 или быть отрицательной!")
    private Double price;

    @Column(name="Tags")
    @NotEmpty(message = "Тег не может быть пустым!")
    @Size(max = 20, message = "Количество тегов не может быть более 20!")
    private List<String> tags;

    @Column(name="Category", nullable = false)
    @NotEmpty(message = "Категории не могут быть пустым!")
    @Length(max = 50, message = "Категорий не может быть более 60!")
    private String category;

    @Column(name="Count", nullable = false)
    @NotNull(message = "Количество не может быть пустым!")
    @PositiveOrZero(message = "Количество не может быть отрицательным!")
    private Integer count;

    @Column(name="Satellite", nullable = false)
    @ManyToOne
    @JoinColumn(name="satelliteId", referencedColumnName = "id")
    @NotNull(message = "Название города не может быть пустым!")
    @City(message = "Укажите название склада из списка: ")
    private SatelliteEntity satellite;

    @Override
    public String toString() {
        return title + ", " + description + ", " + price + ", " + tags + ", " + category + ", " + count + ", " + satellite;
    }

    public String showInfo() {
        return this.showInfo();
    }
}