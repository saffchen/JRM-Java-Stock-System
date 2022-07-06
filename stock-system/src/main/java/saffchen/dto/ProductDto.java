package saffchen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import saffchen.entities.SatelliteEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */

@Data
@AllArgsConstructor
public class ProductDto{
    private Long id;
    private String title;
    private String description;
    private Double price;
    private List<String> tags;
    private String category;
    private Integer count;
    private SatelliteEntity satellite;
}