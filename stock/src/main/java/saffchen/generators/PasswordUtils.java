package saffchen.generators;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Pattern;

public class PasswordUtils {
    public static boolean isStrong(String password){
        Pattern digits = Pattern.compile("[0-9]");
        Pattern letters = Pattern.compile("[a-zA-Z]");
        Pattern specialChars = Pattern.compile("[\\\s!\"#$%&'()*+,-./:;<=>?@^_`{|}\\[\\]]");

        return digits.matcher(password).find()
                && letters.matcher(password).find()
                && specialChars.matcher(password).find();
    }

    public static String encryptByBCrypt(String password){
        return new BCryptPasswordEncoder(10).encode(password);
    }
}