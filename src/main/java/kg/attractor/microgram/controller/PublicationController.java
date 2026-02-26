package kg.attractor.microgram.controller;

import jakarta.validation.Valid;
import kg.attractor.microgram.dto.PublicationCreateDto;
import kg.attractor.microgram.dto.PublicationViewDto;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.service.PublicationService;
import kg.attractor.microgram.util.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("publication")
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;
    private final UserHelper userHelper;

    @GetMapping
    public String showAll(Model model){
        List<PublicationViewDto> publications = publicationService.showPublicationsForMaimPage();
        model.addAttribute("publications", publications);

        return "publication/home";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {

        model.addAttribute("publicationCreateDto",
                new PublicationCreateDto());

        return "publication/create";
    }

    @PostMapping("/create")
    public String createPublication(@Valid PublicationCreateDto publicationCreateDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){
            return "publication/create";
        }
        publicationService.create(publicationCreateDto);

        return "redirect:/users/profile";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        User authUser = userHelper.getAuthUser();
        if (authUser == null) {
            return "redirect:/login";
        }

        publicationService.delete(id);

        return "redirect:/users/profile";
    }

    @GetMapping("/{id}")
    public String showPublication(@PathVariable Integer id, Model model) {
        User authUser = userHelper.getAuthUserForProfileCheck();
        PublicationViewDto publication = publicationService.viewPublication(id);

        model.addAttribute("publication", publication);

        boolean isOwner = authUser != null && authUser.getLogin().equals(publication.getOwner());
        model.addAttribute("isOwner", isOwner);

        return "publication/view";
    }
}
