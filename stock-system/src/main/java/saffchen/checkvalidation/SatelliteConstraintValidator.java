package saffchen.checkvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SatelliteConstraintValidator implements ConstraintValidator<City, SatelliteCity> {

    @Override
    public void initialize(City constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(SatelliteCity satelliteCity, ConstraintValidatorContext constraintValidatorContext) {
        return satelliteCity.getSatellite().equals("Moscow") || satelliteCity.getSatellite().equals("Saint-Petersburg");
    }
}