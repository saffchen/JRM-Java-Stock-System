package saffchen;

import saffchen.checkvalidation.CheckingValidationField;

public class RunApp {
    public static void main(String[] args) {
        CheckingValidationField checkingValidationField = new CheckingValidationField();
        checkingValidationField.constraintViolations();
    }
}