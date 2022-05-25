package saffchen.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
    public static String getBanner() {
        return getInfoFromTxtFile("banner.txt");
    }
    public static String getParticipants() {
        return getInfoFromTxtFile("participants.txt");
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
