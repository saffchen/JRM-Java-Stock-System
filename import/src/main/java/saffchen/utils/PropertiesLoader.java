package saffchen.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties load(String fileName) {
        Properties properties = new Properties();
        try (InputStream in = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(in);
        } catch (IOException e) {
            System.out.println("Can't load properties from file " + fileName);
        }
        return properties;
    }
}
