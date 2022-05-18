package saffchen.checkvalidation;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "satellite_city")
public class SatelliteCity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "satellite_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long satellite_id;
    private String title;
    private String description;
    private int price;
    private List<String> tags;
    private String category;
    private int count;
    private String satellite;


    @OneToOne
    @PrimaryKeyJoinColumn
    private AdditionalProduct Additionalproduct;

    public SatelliteCity() {

    }

    public SatelliteCity(Long satellite_id, String title, String description, int price,
                         List<String> tags, String category, int count, String satellite) {
        this.satellite_id = satellite_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.tags = tags;
        this.category = category;
        this.count = count;
        this.satellite = satellite;
    }

    public Long getSatellite_id() {
        return satellite_id;
    }

    public void setSatellite_id(Long satellite_id) {
        this.satellite_id = satellite_id;
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

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }
}