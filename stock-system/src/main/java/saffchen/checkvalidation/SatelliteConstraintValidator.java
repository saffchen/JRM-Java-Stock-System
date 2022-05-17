package saffchen.checkvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Scanner;

public class SatelliteConstraintValidator implements ConstraintValidator<City, String> {

    @Override
    public void initialize(City constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
    /*@Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        ArrayList<String> cityList = new ArrayList<>();
        cityList.add("Moscow");
        cityList.add("Saint-Petersburg");
        s = new Scanner(System.in).nextLine().trim();
        for (String s1 : cityList) {
            if (s.equals(s1.get)) {
                return true;
            } else return false;
        }
    }*/
    //Доделать валидацию по значению
    //Разобраться с ошибкой