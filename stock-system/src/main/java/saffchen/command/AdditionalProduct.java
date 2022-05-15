package saffchen.command;

import javax.validation.constraints.*;

public class AdditionalProduct {

    @NotEmpty
    @Size(max = 255)
    String title;

    @NotEmpty
    @Size(max = 1024)
    String description;

    @NotEmpty
    @Positive
    int price;

    @NotEmpty
    @Max(20)
    String tags;

    @NotEmpty
    @Size(max = 50)
    String category;

    @NotEmpty
    @Positive
    int count;

    @NotEmpty
    @City
    String satellite;

    public AdditionalProduct(String title, String description, int price, String tags, String category, int count, String satellite) {
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

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getTags() {
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
}