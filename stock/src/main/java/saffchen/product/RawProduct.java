package saffchen.product;

import lombok.Data;
import saffchen.entities.SatelliteEntity;

@Data
public class RawProduct {
    private String title;
    private String description;
    private String price;
    private String tags;
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

    public String toCSVString(String sep) {
        return "\n" + title + sep +
                description + sep +
                price + sep +
                tags + sep +
                category + sep +
                count + sep +
                satellite.toString();
    }
}
