package saffchen.dto;

import lombok.*;

import java.util.List;

/**
 * @author saffchen created on 09.07.2022
 * @project JRM-Java-Stock-System
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoReport {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private List<String> tags;
    private String category;
    private Integer count;
    private String satelliteName;
}