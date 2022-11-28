
package saffchen.generators;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Scope("prototype")
public class CustomSequenceGenerator implements SequenceGenerator {
    private final Byte ASCII_START = 32;
    private final Byte ASCII_STOP = 126;

    @Override
    public String generateSequence(int targetStringLength) {
        Random random = new Random();
        String generatedString = random.ints(ASCII_START, ASCII_STOP + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

}