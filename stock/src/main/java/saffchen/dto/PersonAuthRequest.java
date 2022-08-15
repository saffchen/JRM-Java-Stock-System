package saffchen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonAuthRequest {
    private String username;
    private String password;
}
