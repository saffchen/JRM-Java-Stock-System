package saffchen.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saffchen.dto.UserDto;

@Component
public class CustomUserGenerator implements UserGenerator {
    private final byte MAX_SYMBOLS_IN_FIRST_NAME = 15;
    private final byte MAX_SYMBOLS_IN_LAST_NAME = 15;
    private final byte MAX_SYMBOLS_IN_USERNAME = 15;
    private final byte MAX_SYMBOLS_IN_PASSWORD = 10;
    private final byte MAX_SYMBOLS_IN_USER_DOMAIN = 15;
    private final byte MAX_SYMBOLS_IN_GLOBAL_DOMAIN = 4;
    private final int MAX_SYMBOLS_IN_DESCRIPTION = 256;
    @Autowired
    private CustomSequenceGenerator customSequenceGenerator;
    @Autowired
    private CustomPasswordGenerator password;

    @Override
    public UserDto generate() {
        UserDto user = new UserDto();
        StringBuilder tempString = new StringBuilder();
        user.setDescription(customSequenceGenerator.generateSequence(MAX_SYMBOLS_IN_DESCRIPTION));
        user.setUsername(customSequenceGenerator.generateSequence(MAX_SYMBOLS_IN_USERNAME));
        user.setEmail(tempString.append(user.getUsername())
                .append("@")
                .append(customSequenceGenerator.generateSequence(MAX_SYMBOLS_IN_USER_DOMAIN))
                .append(".").append(customSequenceGenerator.generateSequence(MAX_SYMBOLS_IN_GLOBAL_DOMAIN))
                .toString()
        );
        user.setName(tempString.append(customSequenceGenerator.generateSequence(MAX_SYMBOLS_IN_FIRST_NAME))
                .append(" ")
                .append(customSequenceGenerator.generateSequence(MAX_SYMBOLS_IN_LAST_NAME))
                .toString());
        user.setPassword(password.generate(MAX_SYMBOLS_IN_PASSWORD));
        user.setActive(true);
        user.setRole("ROLE_USER");

        return user;
    }

}