package saffchen.checkvalidation;

import saffchen.utils.FileUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class SatelliteConstraintValidator implements ConstraintValidator<City, String> {
    private static final List<String> SATELLITE = FileUtils.getSatelliteList();

    @Override
    public boolean isValid(String satelliteCity, ConstraintValidatorContext constraintValidatorContext) {
        for (String satellite : SATELLITE) {
            if (satellite.contains(satelliteCity)) {
                return true;
            }
        }
        return false;
    }
}
