package saffchen.checkvalidation;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import java.util.List;

public class Product {
    @NotEmpty(message = "Название не может быть пустым!")
    @Length(max = 255)
    private String title;

    @NotEmpty(message = "Описание не может быть пустым!")
    @Length(max = 1024)
    private String description;

    @NotNull(message = "Цена не может отсутствовать!")
    @Positive
    private int price;

    @NotEmpty(message = "Тег не может быть пустым!")
    @Size(max = 20)
    private List<String> tags;

    @NotEmpty(message = "Категории не могут быть пустым!")
    @Length(max = 50)
    private String category;

    @NotNull(message = "Количество не может быть пустым!")
    @Positive
    private int count;

    @NotNull(message = "Название города не может быть пустым!")
    @City
    private SatelliteCity satellite;

    public Product(String title, String description, int price, List<String> tags, String category, int count, SatelliteCity satellite) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.tags = tags;
        this.category = category;
        this.count = count;
        this.satellite = satellite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SatelliteCity getSatellite() {
        return satellite;
    }

    public void setSatellite(SatelliteCity satellite) {
        this.satellite = satellite;
    }

    @Override
    public String toString() {
        return "AdditionalProduct{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", tags='" + tags + '\'' +
                ", category='" + category + '\'' +
                ", count=" + count +
                ", satellite='" + satellite + '\'' +
                '}';
    }
}