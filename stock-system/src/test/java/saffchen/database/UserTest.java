package saffchen.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user = new User();

    @ParameterizedTest
    @NullSource
    @EmptySource
    void checkPassword(String pass) {
        boolean check = user.checkPassword(pass);
        assertFalse(check);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void checkTheLogin(String login) {
        boolean check = user.checkTheLogin(login);
        assertFalse(check);
    }

    @Test
    void isAuthorized() {

    }
}