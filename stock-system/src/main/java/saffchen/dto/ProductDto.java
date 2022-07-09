package saffchen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author saffchen created on 09.07.2022
 * @project JRM-Java-Stock-System
 */
@Data
@AllArgsConstructor
public class ProductDto {

    private String title;
    private String description;
    private Double price;
    private List<String> tags;
    private String category;
    private Integer count;
    private String satelliteName;
}