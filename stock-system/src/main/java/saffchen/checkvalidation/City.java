package saffchen.checkvalidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.MessageFormat;
import java.util.List;

import static java.text.MessageFormat.format;

@Constraint(validatedBy = SatelliteConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface City {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    }