package saffchen.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PersonAuthResponse {
    private final String jwt;
}
