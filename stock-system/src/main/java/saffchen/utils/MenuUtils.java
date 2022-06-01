package saffchen.utils;

import java.util.List;

public class MenuUtils {

    private static final List<String> POSITIVE_ANSWERS = List.of("ДА", "YES");

    public static boolean isExit(String keyword) {
        return "EXIT". equalsIgnoreCase(keyword.trim());
    }

    public static boolean isYes(String keyword) {
        return POSITIVE_ANSWERS.contains(keyword.trim().toUpperCase());
    }
}

