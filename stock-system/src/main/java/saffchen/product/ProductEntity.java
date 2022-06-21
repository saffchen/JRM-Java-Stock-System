package saffchen.product;

import org.hibernate.validator.constraints.Length;
import saffchen.checkvalidation.City;

import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ProductEntity {

    @NotEmpty(message = "Название не может быть пустым!")
    @Length(max = 255, message = "Название не может быть длиннее 255 символов!")
    private String title;

    @NotEmpty(message = "Описание не может быть пустым!")
    @Length(max = 1024, message = "Описание не может быть длиннее 1024 символов!")
    private String description;

    @NotNull(message = "Цена не может отсутствовать!")
    @Positive(message = "Цена не может быть равна 0 или быть отрицательной!")
    private Double price;

    @NotEmpty(message = "Тег не может быть пустым!")
    @Size(max = 20, message = "Количество тегов не может быть более 20!")
    private List<String> tags;

    @NotEmpty(message = "Категории не могут быть пустым!")
    @Length(max = 50, message = "Категорий не может быть более 60!")
    private String category;

    @NotNull(message = "Количество не может быть пустым!")
    @PositiveOrZero(message = "Количество не может быть отрицательным!")
    private Integer count;

    @NotNull(message = "Название города не может быть пустым!")
    @City(message = "Укажите название склада из списка: ")
    private String satellite;

    public ProductEntity(String title, String description, Double price, List<String> tags, String category, Integer count, String satellite) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.tags = tags;
        this.category = category;
        this.count = count;
        this.satellite = satellite;
    }

    public ProductEntity(Map<String, String> fieldsMap) {
        for (Map.Entry<String, String> entry : fieldsMap.entrySet()) {
            String fieldName = entry.getKey();
            String value = entry.getValue();
            switch (fieldName) {
                case "title":
                    setTitle(value);
                    break;
                case "description":
                    setDescription(value);
                    break;
                case "price":
                    setPrice(Double.parseDouble(value));
                    break;
                case "tags":
                    setTags(Arrays.asList(value.split("\\s")));
                    break;
                case "category":
                    setCategory(value);
                    break;
                case "count":
                    setCount(Integer.parseInt(value));
                    break;
                case "satellite":
                    setSatellite(value);
                    break;
                default:
                    System.out.println("There are no matches");
            }
        }
    }

    public ProductEntity() {

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    @Override
    public String toString() {
        return title + ", " + description + ", " + price + ", " + tags + ", " + category + ", " + count + ", " + satellite;
    }

    public String showInfo() {
        return title + ", " + description + ", " + price + ", " + tags + ", " + category + ", " + count + ", " + satellite;
    }
}
