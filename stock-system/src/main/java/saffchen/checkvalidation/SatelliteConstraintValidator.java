package saffchen.checkvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class SatelliteConstraintValidator implements ConstraintValidator<City, String> {

    @Override
    public void initialize(City constraintAnnotation) {

    }

    @Override
    public boolean isValid(String satelliteCity, ConstraintValidatorContext constraintValidatorContext) {
        List<String> satellites = new ArrayList<>();
        satellites.add("MOSCOW");
        satellites.add("SAINT-PETERSBURG");
        boolean isValidSatellite = false;
        for (String s : satellites) {
            if (s.contains(satelliteCity)){
                isValidSatellite = s.contains(satelliteCity);
                break;
            }
        } return isValidSatellite;
    }
}