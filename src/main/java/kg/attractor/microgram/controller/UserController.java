package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.ProfileDto;
import kg.attractor.microgram.dto.PublicationViewDto;
import kg.attractor.microgram.dto.UserDtoForView;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.service.PublicationService;
import kg.attractor.microgram.service.SubscriptionService;
import kg.attractor.microgram.service.UserService;
import kg.attractor.microgram.util.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PublicationService publicationService;
    private final UserHelper userHelper;
    private final SubscriptionService subscriptionService;


    @GetMapping("profile")
    public String showProfile(@RequestParam(required = false) String email, Model model){

        User authUser = userHelper.getAuthUserForProfileCheck();
        ProfileDto profile = userService.getProfile(email, authUser);

        boolean isSubscribed = false;

        if (authUser != null && !authUser.getEmail().equals(profile.getUser().getEmail())) {
            isSubscribed = subscriptionService.isSubscribed(authUser.getEmail(), profile.getUser().getEmail());
        }

        model.addAttribute("isSubscribed", isSubscribed);
        model.addAttribute("profileUser", profile.getUser());
        model.addAttribute("isOwner", profile.getIsOwner());
        model.addAttribute("isAuthenticated", authUser != null);
        model.addAttribute("publications", publicationService.viewAllUserPublications(profile.getUser().getEmail()));

        return "users/profile";
    }

    @GetMapping("search")
    public String searchUsers(Model model,
                              @RequestParam(required = false) String word,
                              @RequestParam(required = false) String search){

        if (word != null && search != null && !word.isBlank()) {
            List<UserDtoForView> foundUsers = userService.searchUsers(word, search);
            model.addAttribute("foundUsers", foundUsers);
        }

        model.addAttribute("word", word);
        model.addAttribute("search", search);

        return "users/search";
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

    @PostMapping("/subscribe")
    @ResponseBody
    public Map<String, Object> subscribe(@RequestParam String subscriberEmail) {

        User authUser = userHelper.getAuthUser();

        boolean subscribed = subscriptionService
                .toggleSubscription(authUser.getEmail(), subscriberEmail);

        User profileUser = userService.getUserByEmail(subscriberEmail);
        User me = userService.getUserByEmail(authUser.getEmail());

        return Map.of(
                "subscribed", subscribed,
                "followers",  profileUser.getFollowers(),
                "subscriptions", profileUser.getSubscriptions()
        );
    }
}
