package saffchen.dto;

import lombok.Getter;

@Getter
public class PersonAuthResponse {
    private final String jwt;

    public PersonAuthResponse(String jwt) {
        this.jwt = jwt;
    }
}
