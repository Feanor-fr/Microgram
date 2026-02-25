package kg.attractor.microgram.util;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.model.User;

public class ConvertHelper {

    private ConvertHelper() {
    }

    public static User convertTouser(UserDto userDto){

        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .login(userDto.getLogin())
                .userName(userDto.getUserName())
                .description(userDto.getDescription())
                .followers(0)
                .subscriptions(0)
                .enabled(true)
                .build();
    }


}
