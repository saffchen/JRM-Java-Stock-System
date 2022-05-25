package saffchen.checkvalidation;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import java.util.List;

public class Product {
    @NotEmpty(message = "Название не может быть пустым!")
    @Length(max = 255, message = "Название не может быть длиннее 255 символов")
    private String title;

    @NotEmpty(message = "Описание не может быть пустым!")
    @Length(max = 1024, message = "Описание не может быть длиннее 1024 символов")
    private String description;

    @NotNull(message = "Цена не может отсутствовать!")
    @Positive(message = "Цена не может быть равна 0 или быть отрицательной")
    private int price;

    @NotEmpty(message = "Тег не может быть пустым!")
    @Size(max = 20, message = "Количество тегов не может быть более 20")
    private List<String> tags;

    @NotEmpty(message = "Категории не могут быть пустым!")
    @Length(max = 50, message = "Категорий не может быть более 60")
    private String category;

    @NotNull(message = "Количество не может быть пустым!")
    @PositiveOrZero(message = "Количество не может быть отрицательным")
    private int count;

    @NotNull(message = "Название города не может быть пустым!")
    @City
    private String satellite;

    public Product(String title, String description, int price, List<String> tags, String category, int count, String satellite) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.tags = tags;
        this.category = category;
        this.count = count;
        this.satellite = satellite;
    }

    @Override
    public String toString() {
        return "AdditionalProduct" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", tags='" + tags + '\'' +
                ", category='" + category + '\'' +
                ", count=" + count +
                ", satellite='" + satellite;
    }
}