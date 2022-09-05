package saffchen.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserAuthResponse {
    private final String jwt;
}
