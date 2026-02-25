package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.ProfileDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.dto.UserDtoForView;
import kg.attractor.microgram.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void create(UserDto userDto);

    User getUserByEmail(String email);

    User getUserByIdForUserHelper(String email);

    ProfileDto getProfile(String targetEmail, User authUser);

    Boolean isEmailExist(String email);

    Boolean isLoginExist(String login);

    void photoForProfile(MultipartFile file, String email);
}
