package saffchen.checkvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import saffchen.utils.FileUtils;
import java.util.List;

public class SatelliteConstraintValidator implements ConstraintValidator<City, String> {
    private static List<String> SATELLITE = FileUtils.getSatellites();

    @Override
    public boolean isValid(String satelliteCity, ConstraintValidatorContext constraintValidatorContext) {
        for (String satellite : SATELLITE) {
            if (satellite.contains(satelliteCity)) {
                return true;
            }
        } return false;
    }
}