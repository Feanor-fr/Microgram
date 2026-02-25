package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.UserDto;

public interface UserService {
    void create(UserDto userDto);

    Boolean isEmailExist(String email);

    Boolean isLoginExist(String login);
}
