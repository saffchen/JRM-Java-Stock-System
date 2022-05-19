package saffchen.checkvalidation;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.util.List;

public class AdditionalProduct {
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
    @OneToOne(mappedBy = "satellite", cascade = CascadeType.ALL)
    private SatelliteCity satellite;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getCategory() {
        return category;
    }

    public int getCount() {
        return count;
    }

    public SatelliteCity getSatellite() {
        return satellite;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCount(int count) {
        this.count = count;
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