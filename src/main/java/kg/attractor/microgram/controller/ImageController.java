package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.ImageDto;
import kg.attractor.microgram.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{filename}")
    public ResponseEntity<?> getImage(@PathVariable String filename){
        return imageService.getById(filename);
    }

    @PostMapping
    public HttpStatus create(ImageDto imageDto){
        imageService.create(imageDto);
        return HttpStatus.CREATED;
    }

    @GetMapping("/pub/{filename}")
    public ResponseEntity<?> getPublicationImage(@PathVariable String filename){
        return imageService.downloadFile(filename, "publications");
    }
}
