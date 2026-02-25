package kg.attractor.microgram.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import kg.attractor.microgram.customAnnotations.UniqueUserValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@UniqueUserValues
public class UserDto {

    @NotBlank(message = "Поле email не может быть пустым!")
    @Email(message = "Поле должно быть указанно в формате qwe@.qwe")
    private String email;

    @NotBlank(message = "Поле пароль не должно быть пустым!")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Пароль должен содержать хотя бы одну заглавную букву и одну цифру!")
    private String password;

    @NotBlank(message = "Поле login не может быть пустым!")
    private String login;

    @NotBlank(message = "Поле name не может быть пустым!")
    private String userName;

    @NotBlank(message = "Поле name не может быть пустым!")
    private String description;
}
