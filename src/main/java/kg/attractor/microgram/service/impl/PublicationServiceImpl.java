package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.PublicationCreateDto;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.repository.PublicationRepository;
import kg.attractor.microgram.service.ImageService;
import kg.attractor.microgram.service.PublicationService;
import kg.attractor.microgram.service.UserService;
import kg.attractor.microgram.util.ConvertHelper;
import kg.attractor.microgram.util.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {
    private final PublicationRepository repository;
    private final UserService userService;
    private final ImageService imageService;
    private final UserHelper userHelper;

    @Override
    public void create(PublicationCreateDto publicationDto){
        String fileName = imageService.saveUploadedFile(publicationDto.getFile(), "publications");
        User owner = userService.getUserByEmail(userHelper.getAuthUser().getEmail());


        repository.saveAndFlush(ConvertHelper.convertToPublication(publicationDto, fileName, owner));
    }
}
