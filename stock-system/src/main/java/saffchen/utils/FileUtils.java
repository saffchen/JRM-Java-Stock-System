package saffchen.utils;

import saffchen.database.User;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class FileUtils {
    public static String getBanner() {
        return getInfoFromTxtFile("banner.txt");
    }

    public static String getParticipants() {
        return getInfoFromTxtFile("participants.txt");
    }

    public static List<String> getSatelliteList() {
        return Collections.singletonList(getInfoFromTxtFile("satellite.txt"));
    }

    public static String getSatelliteString() {
        return getInfoFromTxtFile("satellite.txt");
    }

    public static List<User> getUsersFromFile(String path) {
        List<User> users = new ArrayList<>();
        String str = null;
        if (path == null || path.isEmpty())
            return users;
        try (Scanner reader = new Scanner(new InputStreamReader(
                FileUtils.class.getResourceAsStream("/" + path)))) {
            while (reader.hasNext()) {
                String[] splitedStr = reader.nextLine().trim().split(";");
                User user = new User();
                Field[] fields = user.getClass().getDeclaredFields();
                for (int i = 0; i < splitedStr.length; i++) {
                    fields[i].setAccessible(true);
                    fields[i].set(user, splitedStr[i]);
                }
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: Unknown error");
        }
        return users;
    }

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
