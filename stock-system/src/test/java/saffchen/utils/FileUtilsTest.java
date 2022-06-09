package saffchen.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import saffchen.database.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {
    @ParameterizedTest
    @EmptySource
    @NullSource
    @ValueSource(strings = {"Wrong string \b.txt "})
    void getUsersFromFile(String filePath) {
        List<User> users = FileUtils.getUsersFromFile(filePath);
        assertEquals(new ArrayList<User>(), users);
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    @ValueSource(strings = {"Wrong string \b.txt "})
    void getInfoFromTxtFile(String filePath) {
        String info = FileUtils.getInfoFromTxtFile(filePath);
        assertEquals("", "");
    }
}