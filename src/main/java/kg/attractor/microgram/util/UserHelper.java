package kg.attractor.microgram.util;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHelper {
    private final UserService userService;

    public User getAuthUser(){
        return userService
                .getUserByEmail(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
    }

    public User getAuthUserForProfileCheck() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return null;
        }

        String email = auth.getName();

        return userService.getUserByIdForUserHelper(email);
    }
    }
