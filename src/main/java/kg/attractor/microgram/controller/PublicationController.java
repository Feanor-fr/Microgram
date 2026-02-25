package kg.attractor.microgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("publication")
@RequiredArgsConstructor
public class PublicationController {

    @GetMapping
    public String showAll(){
        return "publication/home";
    }
}
