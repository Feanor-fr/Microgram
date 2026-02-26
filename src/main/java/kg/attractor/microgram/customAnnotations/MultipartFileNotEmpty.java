package kg.attractor.microgram.customAnnotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import kg.attractor.microgram.customAnnotations.validators.MultipartFileNotEmptyValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultipartFileNotEmptyValidator.class)
public @interface MultipartFileNotEmpty {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
