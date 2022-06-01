package saffchen.utils;

/**
 * @author alex_jd on 6/1/22
 * @project JRM-Java-Stock-System
 */
public class ValidationUtil {

    public static boolean isStringToDoubleValid(String checkingString) {
        try{
            double price = Double.parseDouble(checkingString);
            return price > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isStringToIntegerValid(String checkingString) {
        try{
            int count = Integer.parseInt(checkingString);
            return count >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
