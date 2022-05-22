package saffchen.checkvalidation;

import java.io.Serial;
import java.io.Serializable;
import javax.persistence.*;

public class SatelliteCity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String satellite;

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
