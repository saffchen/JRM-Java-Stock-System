package saffchen.checkvalidation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import java.util.Collections;
import java.util.Set;

public class CheckingValidationField {
    public static void main(String[] args) {
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Product additionalProduct = new Product("Iphone 12", "best camera", -1200, Collections.singletonList("phone"), "phone",
                20, "Moscow");

        Set<ConstraintViolation<Product>> violations = validator.validate(additionalProduct);
        System.out.println(violations);
    }
}