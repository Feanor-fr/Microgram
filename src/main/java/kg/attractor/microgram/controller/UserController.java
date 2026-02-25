package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.ProfileDto;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.service.UserService;
import kg.attractor.microgram.util.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserHelper userHelper;


    @GetMapping("profile")
    public String showProfile(@RequestParam(required = false) String email, Model model){

        User authUser = userHelper.getAuthUserForProfileCheck();
        ProfileDto profile = userService.getProfile(email, authUser);

        model.addAttribute("profileUser", profile.getUser());
        model.addAttribute("isOwner", profile.getIsOwner());
        model.addAttribute("isAuthenticated", authUser != null);

        return "users/profile";
    }

    @PostMapping("/avatar")
    public String uploadAvatar(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/users/profile";
        }

        User userAuth = userHelper.getAuthUser();
        userService.photoForProfile(file, userAuth.getEmail());
        return "redirect:/users/profile";
    }
}
