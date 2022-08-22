package saffchen.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import saffchen.entities.SatelliteEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawProduct {
    private String id;
    private String title;
    private String description;
    private String price;
    private String tags;
    private String category;
    private String count;
    private SatelliteEntity satellite;

    public String toCSVString(String sep) {
        return "\n" + id + sep +
                title + sep +
                description + sep +
                price + sep +
                tags + sep +
                category + sep +
                count + sep +
                satellite.toString();
    }
}
