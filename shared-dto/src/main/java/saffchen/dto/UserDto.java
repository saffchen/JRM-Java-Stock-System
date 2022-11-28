package saffchen.dto;
import lombok.Data;

@Data
public class UserDto {
    private String role;
    private String name;
    private String username;
    private String email;
    private String password;
    private Boolean active;
    private String description;
}