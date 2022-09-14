package saffchen.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {

    private Long id;
    private String name;
    private String description;
    private long count;
}
