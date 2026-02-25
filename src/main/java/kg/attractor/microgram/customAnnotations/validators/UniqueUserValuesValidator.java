package kg.attractor.microgram.customAnnotations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.microgram.customAnnotations.UniqueUserValues;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueUserValuesValidator implements ConstraintValidator<UniqueUserValues, UserDto> {
    private final UserService userService;


    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {

        if(userDto == null) return true;

        Boolean isEmailExist = userService.isEmailExist(userDto.getEmail());
        Boolean isLoginExist = userService.isLoginExist(userDto.getLogin());

        if (!isEmailExist && !isLoginExist) {
            return true;
        }

        context.disableDefaultConstraintViolation();

        if (isEmailExist) {
            context.buildConstraintViolationWithTemplate("Такой email уже зарегистрирован!")
                    .addPropertyNode("email")
                    .addConstraintViolation();
        }

        if (isLoginExist) {
            context.buildConstraintViolationWithTemplate("Такой login уже существует!")
                    .addPropertyNode("login")
                    .addConstraintViolation();
        }

        return false;

    }
}
