package saffchen.checkvalidation;

import saffchen.command.AdditionalProduct;

import javax.validation.*;
import java.util.Set;

public class CheckingValidationField {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    Set<ConstraintViolation<AdditionalProduct>> violations = validator.validate(new AdditionalProduct());
}