package saffchen.checkvalidation;

import saffchen.command.City;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SatelliteConstraintValidator implements ConstraintValidator<City, String> {

    @Override
    public void initialize(City constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.equals("Saint-Petersburg") || s.equals("Moscow")) {
            return true;
        } else return false;
    }
}