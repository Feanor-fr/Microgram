package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.PublicationCreateDto;
import kg.attractor.microgram.dto.PublicationViewDto;
import kg.attractor.microgram.model.Publication;

import java.util.List;

public interface PublicationService {
    void create(PublicationCreateDto publicationDto);

    Publication findById(Integer id);

    void save(Publication publication);

    void delete(Integer id);

    PublicationViewDto viewPublication(Integer id);

    List<PublicationViewDto> viewAllUserPublications(String email);

    List<PublicationViewDto> showPublicationsForMaimPage();
}
