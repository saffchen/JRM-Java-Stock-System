package saffchen.checkvalidation;

import javax.validation.constraints.*;
import java.util.List;

public class AdditionalProduct {
    @NotEmpty(message = "Название не может быть пустым!")
    @Size(max = 255)
    private String title;

    @NotEmpty(message = "Описание не может быть пустым!")
    @Size(max = 1024)
    private String description;

    @NotNull(message = "Цена не может отсутствовать!")
    @Positive
    private int price;

    @NotBlank(message = "Тег не может быть пустым!")
    @Max(20)
    private List<String> tags;

    @NotEmpty(message = "Категории не могут быть пустым!")
    @Size(max = 50)
    private String category;

    @NotNull(message = "Количество не может быть пустым!")
    @Positive
    private int count;

    @NotEmpty(message = "Название города не может быть пустым!")
    //@City
    private String satellite;

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

    public String getSatellite() {
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

    public void setSatellite(String satellite) {
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