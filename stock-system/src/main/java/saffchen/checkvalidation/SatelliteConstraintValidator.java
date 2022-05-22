package saffchen.checkvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SatelliteConstraintValidator implements ConstraintValidator<City, String> {

    @Override
    public void initialize(City constraintAnnotation) {

    }

    @Override
    public boolean isValid(String satelliteCity, ConstraintValidatorContext constraintValidatorContext) {
        return satelliteCity.equals("Moscow") || satelliteCity.equals("Saint-Petersburg");
    }
}