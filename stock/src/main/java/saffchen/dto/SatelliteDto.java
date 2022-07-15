package saffchen.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteDto {
    private long id;
    private String name;
    private String description;
    private long count;
}
