package saffchen.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user = new User();

    @Test
    void checkPassword() {
        boolean check = user.checkPassword(null);
        assertFalse(check);
        check = user.checkPassword("");
        assertFalse(check);

    }

    @Test
    void checkTheLogin() {
        boolean check = user.checkTheLogin(null);
        assertFalse(check);
        check = user.checkTheLogin("");
        assertFalse(check);
    }

    @Test
    void isAuthorized() {

    }
}