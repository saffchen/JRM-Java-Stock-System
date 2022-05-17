package saffchen.checkvalidation;

import javax.validation.*;
import java.util.Set;

public class CheckingValidationField {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    public Set<ConstraintViolation<AdditionalProduct>> constraintViolations() {
        Set<ConstraintViolation<AdditionalProduct>> violations = validator.validate(new AdditionalProduct("Iphone 12 Pro Max 512",
                "Iphone with big display and lidar camera", 1024,
                "Phone, Camera", "Phone", 15, "Moscow"));
        return violations;
}
}