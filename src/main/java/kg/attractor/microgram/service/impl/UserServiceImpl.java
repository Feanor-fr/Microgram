package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.ProfileDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.exception.UserNotFoundException;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.repository.UserRepository;
import kg.attractor.microgram.service.ImageService;
import kg.attractor.microgram.service.RoleService;
import kg.attractor.microgram.service.UserService;
import kg.attractor.microgram.util.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final RoleService roleService;
    private final ImageService imageService;

    @Override
    public void create(UserDto userDto){

        User user = ConvertHelper.convertTouser(userDto);
        user.setRole(roleService.findRoleById(1));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        repository.saveAndFlush(user);
    }

    @Override
    public User getUserByEmail(String email){

        return repository.findById(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByIdForUserHelper(String email){

        return repository.findById(email).orElse(null);
    }

    @Override
    public ProfileDto getProfile(String checkEmail, User authUser) {

        String finalEmail = (checkEmail == null && authUser != null) ? authUser.getEmail() : checkEmail;

        if (finalEmail == null) {
            throw new UserNotFoundException();
        }

        User user = repository.findById(finalEmail)
                .orElseThrow(UserNotFoundException::new);

        return ProfileDto.builder()
                .user(ConvertHelper.convertToUserDtoForView(user))
                .isOwner(authUser != null && authUser.getEmail().equals(user.getEmail()))
                .build();
    }

    @Override
    public Boolean isEmailExist(String email){
        return repository.existsById(email);
    }

    @Override
    public Boolean isLoginExist(String login){
        return repository.existsByLogin(login);
    }


    @Override
    public void photoForProfile(MultipartFile file, String email){

        User user = repository.findById(email)
                .orElseThrow(UserNotFoundException::new);

        String oldAva = user.getAvatar();

        String newAva = imageService.saveUploadedFile(file, "images");

        user.setAvatar(newAva);
        imageService.deleteFile(oldAva, "images");

        repository.saveAndFlush(user);
    }
}
