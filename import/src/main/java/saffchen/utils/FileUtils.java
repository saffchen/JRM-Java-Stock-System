package saffchen.utils;

import saffchen.database.User;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class FileUtils {
    public static String getInfoFromTxtFile(String filePath) {
        StringBuilder builder = new StringBuilder();
        try (FileReader reader = new FileReader(filePath)) {
            while (reader.ready()) {
                builder.append((char) reader.read());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error: Can't find the file");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Can't read the file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: Unknown error");
        }
        return builder.toString();
    }
}
