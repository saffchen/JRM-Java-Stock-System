package saffchen.database;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileConnectionTest {

    @Test
    void getInstance() {
        FileConnection instance = FileConnection.getInstance("filePath");
        assertEquals(instance, FileConnection.getInstance("newInstance"));
    }
}