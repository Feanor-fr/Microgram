package kg.attractor.microgram.controller;

import jakarta.validation.Valid;
import kg.attractor.microgram.dto.PublicationCreateDto;
import kg.attractor.microgram.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("publication")
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;

    @GetMapping
    public String showAll(){
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
}
