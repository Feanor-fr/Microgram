package kg.attractor.microgram.customAnnotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import kg.attractor.microgram.customAnnotations.validators.UniqueUserValuesValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserValuesValidator.class)
public @interface UniqueUserValues {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
