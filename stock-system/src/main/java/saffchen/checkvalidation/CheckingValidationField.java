package saffchen.checkvalidation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class CheckingValidationField {
    public static void main(String[] args) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        AdditionalProduct additionalProduct = new AdditionalProduct();

        Set<ConstraintViolation<AdditionalProduct>> violations = validator.validate(additionalProduct);

        for (ConstraintViolation<AdditionalProduct> constr: violations){

            StringBuilder stringBuilder = new StringBuilder("Property: ");
            stringBuilder.append(constr.getPropertyPath());
            stringBuilder.append("Value");
            stringBuilder.append(constr.getInvalidValue());
            stringBuilder.append("Message");
            stringBuilder.append(constr.getMessage());

            System.out.println(stringBuilder.toString());
        }
    }
}