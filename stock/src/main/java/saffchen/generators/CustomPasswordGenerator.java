package saffchen.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordGenerator implements PasswordGenerator {
    @Autowired
    private CustomSequenceGenerator sequenceGenerator;

    @Override
    public String generate(int len) {
        while (true) {
            String strongPassword = sequenceGenerator.generateSequence(len);
            if (PasswordUtils.isStrong(strongPassword)) {
                //return strongPassword;
                return PasswordUtils.encryptByBCrypt(strongPassword);
            }
        }
    }
}