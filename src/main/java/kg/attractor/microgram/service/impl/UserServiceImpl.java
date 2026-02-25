package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.repository.UserRepository;
import kg.attractor.microgram.service.RoleService;
import kg.attractor.microgram.service.UserService;
import kg.attractor.microgram.util.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final RoleService roleService;

    @Override
    public void create(UserDto userDto){

        User user = ConvertHelper.convertTouser(userDto);
        user.setRole(roleService.findRoleById(1));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        repository.saveAndFlush(user);
    }

    @Override
    public Boolean isEmailExist(String email){
        return repository.existsById(email);
    }

    @Override
    public Boolean isLoginExist(String login){
        return repository.existsByLogin(login);
    }

}
