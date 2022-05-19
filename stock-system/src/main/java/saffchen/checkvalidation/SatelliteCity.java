package saffchen.checkvalidation;

import java.io.Serial;
import java.io.Serializable;
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
    private String satellite;

    @OneToOne
    @PrimaryKeyJoinColumn
    private AdditionalProduct Additionalproduct;

    public SatelliteCity() {

    }

    public SatelliteCity(String satellite) {
        this.satellite = satellite;
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }
}