package saffchen.product;

import saffchen.entities.SatelliteEntity;


import java.util.Objects;

public class RawProduct {
    private String title;
    private String description;
    private String price;
    private String tags ;
    private String category;
    private String count;
    private SatelliteEntity satellite;

    public RawProduct() {

    }

    public RawProduct(String title, String description, String price, String tags,
                      String category, String count, SatelliteEntity satellite) {
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

    public void setSatellite(SatelliteEntity satellite) {
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

    public SatelliteEntity getSatellite() {
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
                ", satellite='" + satellite.toString() + '\'' +
                '}';
    }

    public String toCSVString(String sep){
        return "\n" + title + sep +
                description + sep +
                price + sep +
                tags + sep +
                category + sep +
                count + sep +
                satellite.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawProduct that = (RawProduct) o;
        return title.equals(that.title) &&
                description.equals(that.description) &&
                price.equals(that.price) &&
                tags.equals(that.tags) &&
                category.equals(that.category) &&
                count.equals(that.count) &&
                satellite.equals(that.satellite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, price, tags, category, count, satellite);
    }
}
