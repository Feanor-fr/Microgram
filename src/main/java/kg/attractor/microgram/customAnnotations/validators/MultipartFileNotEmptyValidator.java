package kg.attractor.microgram.customAnnotations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.microgram.customAnnotations.MultipartFileNotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileNotEmptyValidator
        implements ConstraintValidator<MultipartFileNotEmpty, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file,
                           ConstraintValidatorContext context) {

        if (file == null) return false;

        return !file.isEmpty();
    }
}
