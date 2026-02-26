package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.PublicationCreateDto;
import kg.attractor.microgram.dto.PublicationViewDto;

import java.util.List;

public interface PublicationService {
    void create(PublicationCreateDto publicationDto);

    void delete(Integer id);

    PublicationViewDto viewPublication(Integer id);

    List<PublicationViewDto> viewAllUserPublications(String email);

    List<PublicationViewDto> showPublicationsForMaimPage();
}
