package saffchen.utils;

public class MenuUtils {
    public static boolean isExit(String header){
        return !(header.trim().toUpperCase().equals("EXIT"))?true:false;
    }
}
