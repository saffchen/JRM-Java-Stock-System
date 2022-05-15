package saffchen.command;

import saffchen.checkvalidation.SatelliteConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SatelliteConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface City {
    String enterCity() default "{satellite}";
    Class<?> [] groups() default {};
    Class <? extends Payload>[] payload() default {};
}