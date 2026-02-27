package kg.attractor.microgram.controller;

import jakarta.validation.Valid;
import kg.attractor.microgram.dto.CommentCreateDto;
import kg.attractor.microgram.dto.CommentViewDto;
import kg.attractor.microgram.dto.PublicationCreateDto;
import kg.attractor.microgram.dto.PublicationViewDto;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.service.CommentService;
import kg.attractor.microgram.service.PublicationService;
import kg.attractor.microgram.util.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("publication")
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;
    private final UserHelper userHelper;
    private final CommentService commentService;

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

        boolean isOwner = authUser != null && authUser.getEmail().equals(publication.getOwner());
        model.addAttribute("isOwner", isOwner);

        model.addAttribute("comments",
                commentService.getAllByPublication(id));

        model.addAttribute("commentCreateDto",
                new CommentCreateDto());

        return "publication/view";
    }

//    @PostMapping("/comment")
//    public String addComment(@Valid CommentCreateDto commentCreateDto,
//                             BindingResult bindingResult,
//                             Model model) {
//
//        if (bindingResult.hasErrors()) {
//            PublicationViewDto publication =
//                    publicationService.viewPublication(commentCreateDto.getPublicationId());
//
//            model.addAttribute("publication", publication);
//            model.addAttribute("comments",
//                    commentService.getAllByPublication(commentCreateDto.getPublicationId()));
//
//            return "publication/view";
//        }
//
//        commentService.create(commentCreateDto);
//
//        return "redirect:/publication/" + commentCreateDto.getPublicationId();
//    }
//
//    @PostMapping("/comment/delete/{id}")
//    public String deleteComment(@PathVariable Integer id,
//                                @RequestParam Integer publicationId) {
//
//        commentService.delete(id);
//
//        return "redirect:/publication/" + publicationId;
//    }

    @PostMapping("comment/")
    @ResponseBody
    public Map<String, Object> addComment(@Valid @RequestBody CommentCreateDto commentCreateDto) {

        User authUser = userHelper.getAuthUser();
        CommentViewDto comment = commentService.createAndReturnView(commentCreateDto);

        Map<String, Object> response = new HashMap<>();
        response.put("comment", comment);
        response.put("commentsCount", publicationService.findById(commentCreateDto.getPublicationId()).getComments());
        return response;
    }

    @PostMapping("comment/delete/{id}")
    @ResponseBody
    public Map<String, Object> deleteComment(@PathVariable Integer id, @RequestParam Integer publicationId) {
        commentService.delete(id);

        Map<String, Object> response = new HashMap<>();
        response.put("commentsCount", publicationService.findById(publicationId).getComments());
        return response;
    }
}
