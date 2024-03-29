package saffchen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author saffchen created on 25.08.2022
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoReport {
    private String id;
    private String title;
    private String description;
    private Double price;
    private List<String> tags;
    private String category;
    private Integer count;
    private String store;
}