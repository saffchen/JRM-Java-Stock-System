package saffchen.checkvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.google.common.io.Files.readLines;

public class SatelliteConstraintValidator implements ConstraintValidator<City, String> {
    private static List<String> SATELLITE;
    static {
        try {
            SATELLITE = readLines(new File("satellite.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isValid(String satelliteCity, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValidSatellite = false;
        for (String satellite : SATELLITE) {
            if (satellite.contains(satelliteCity)) {
                isValidSatellite = satellite.contains(satelliteCity);
                break;
            }
        }
        return isValidSatellite;
    }
}