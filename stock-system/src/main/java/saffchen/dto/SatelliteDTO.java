package saffchen.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import saffchen.entities.ProductEntity;

import javax.persistence.*;
import java.util.List;
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SatelliteDTO {
    private long id;
    private String name;
    private String description;
    private long count;
}
