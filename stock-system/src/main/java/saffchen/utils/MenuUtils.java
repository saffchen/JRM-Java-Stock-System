package saffchen.utils;

public class MenuUtils {
    public static boolean isExit(String keyword) {
        return !(keyword.trim().toUpperCase().equals("EXIT")) ? true : false;
    }
}
