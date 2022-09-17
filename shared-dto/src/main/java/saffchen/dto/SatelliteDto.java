package saffchen.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteDto {

    private Long id;
    private String name;
    private String description;
    private long count;
}
