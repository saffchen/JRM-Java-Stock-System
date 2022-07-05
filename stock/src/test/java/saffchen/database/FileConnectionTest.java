package saffchen.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileConnectionTest {

    @Test
    void getInstance() {
        FileConnection instance = FileConnection.getInstance("test.csv");
        assertEquals(instance, FileConnection.getInstance("newInstance"));
    }
}