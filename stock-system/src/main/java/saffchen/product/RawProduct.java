package saffchen.product;

import java.util.ArrayList;
import java.util.List;

public class RawProduct {
    private String title;
    private String description;
    private String price;
    private String tags ;
    private String category;
    private String count;
    private String satellite;

    public RawProduct() {

    }

    public RawProduct(String title, String description, String price, String tags,
                      String category, String count, String satellite) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.tags = tags;
        this.category = category;
        this.count = count;
        this.satellite = satellite;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getTags() {
        return tags;
    }

    public String getCategory() {
        return category;
    }

    public String getCount() {
        return count;
    }

    public String getSatellite() {
        return satellite;
    }

    public String showInfo() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", tags=" + tags +
                ", category='" + category + '\'' +
                ", count=" + count +
                ", satellite='" + satellite + '\'' +
                '}';
    }

    public String toCSVString(String sep){
        return title + sep +
                description + sep +
                price + sep +
                tags + sep +
                category + sep +
                count + sep +
                satellite;
    }

}
