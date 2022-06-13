package saffchen.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

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
    void isAuthorized() throws NoSuchFieldException, IllegalAccessException {
            Field authorized = user.getClass().getDeclaredField("authorized");
            HashMap<String, Boolean> auth = new HashMap<>();
            auth.put("password", Boolean.TRUE);
            auth.put("login", Boolean.TRUE);
            authorized.setAccessible(true);
            authorized.set(user, auth);
            assertTrue(user.isAuthorized());

            auth.put("password", Boolean.FALSE);
            auth.put("login", Boolean.TRUE);
            authorized.setAccessible(true);
            authorized.set(user, auth);
            assertFalse(user.isAuthorized());
    }
}