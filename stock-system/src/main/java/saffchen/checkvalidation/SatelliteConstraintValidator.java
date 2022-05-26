package saffchen.checkvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class SatelliteConstraintValidator implements ConstraintValidator<City, String> {
    private static final List<String> SATELLITE = List.of("MOSCOW", "SAINT-PETERSBURG");

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