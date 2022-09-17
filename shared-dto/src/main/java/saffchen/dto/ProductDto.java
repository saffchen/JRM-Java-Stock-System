package saffchen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @author saffchen created on 09.07.2022
 * @project JRM-Java-Stock-System
 */
@Data
@Getter
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private List<String> tags;
    private String category;
    private Integer count;
    private String satelliteName;
}