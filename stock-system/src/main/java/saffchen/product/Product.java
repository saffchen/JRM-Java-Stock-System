package saffchen.product;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String title;
    private String description;
    private Double price;
    private List<String> tags;
    private String category;
    private Integer count;
    private String satellite;

    public Product(){

    }

    public Product(String title, String description, Double price, List<String> tags,
                   String category, Integer count, String satellite) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.tags = tags;
        this.category = category;
        this.count = count;
        this.satellite = satellite;
    }

    public void setTitle(String title){
        this.title = title;
    };
    public void setDescription(String description){
        this.description = description;
    };

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getCategory() {
        return category;
    }

    public Integer getCount() {
        return count;
    }

    public String getSatellite() {
        return satellite;
    }
}
