package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.PublicationCreateDto;
import kg.attractor.microgram.dto.PublicationViewDto;
import kg.attractor.microgram.exception.PublicationNotFoundException;
import kg.attractor.microgram.exception.WrongActionException;
import kg.attractor.microgram.model.Publication;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.repository.PublicationRepository;
import kg.attractor.microgram.service.ImageService;
import kg.attractor.microgram.service.LikeService;
import kg.attractor.microgram.service.PublicationService;
import kg.attractor.microgram.service.UserService;
import kg.attractor.microgram.util.ConvertHelper;
import kg.attractor.microgram.util.UserHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {
    private final PublicationRepository repository;
    private final UserService userService;
    private final ImageService imageService;
    private final UserHelper userHelper;
    private final LikeService likeService;

    @Override
    public void create(PublicationCreateDto publicationDto){
        String fileName = imageService.saveUploadedFile(publicationDto.getFile(), "publications");
        User owner = userService.getUserByEmail(userHelper.getAuthUser().getEmail());


        repository.saveAndFlush(ConvertHelper.convertToPublication(publicationDto, fileName, owner));
    }

    @Override
    public Publication findById(Integer id){

        return repository.findById(id)
                .orElseThrow(PublicationNotFoundException::new);
    }

    @Override
    public void save(Publication publication){
        repository.saveAndFlush(publication);
    }

    @Override
    public void delete(Integer id){
        User authUser = userHelper.getAuthUser();

        Publication publication = repository.findById(id)
                .orElseThrow(PublicationNotFoundException::new);

        if(!publication.getOwner().getEmail().equals(authUser.getEmail())){
            throw new WrongActionException("Удалять публикацию может только ее автор!");
        }

        imageService.deleteFile(publication.getPhoto(), "publications");

        repository.delete(publication);
    }

    @Override
    public PublicationViewDto viewPublication(Integer id){
        PublicationViewDto view = ConvertHelper.convertToPublicationViewDto(
        repository.findById(id)
                .orElseThrow(PublicationNotFoundException::new));

        log.info("file_name {}", view.getPhoto());

        return view;
    }

    @Override
    public List<PublicationViewDto> viewAllUserPublications(String email){

        return repository
                .findPublicationsByOwner_Email(email)
                .stream()
                .map(ConvertHelper::convertToPublicationViewDto)
                .toList();
    }

    @Override
    public List<PublicationViewDto> showPublicationsForMaimPage() {
        User authUser = userHelper.getAuthUserForProfileCheck();

        List<Publication> publications;

        if (authUser == null) {
            publications = repository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
        } else {
            publications = repository.findPublicationsFromSubscriptions(authUser.getEmail());

//            if (publications.isEmpty()) {
//                publications = repository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
//            }
        }

        return publications.stream()
                .map(
                        pub -> {
                            PublicationViewDto publicationViewDto = ConvertHelper.convertToPublicationViewDto(pub);

                            if (authUser != null) {
                                Boolean liked = likeService
                                        .existsByOwnerLike_EmailAndPublication_Id(
                                                authUser.getEmail(),
                                                pub.getId()
                                        );
                                publicationViewDto.setLikedByCurrentUser(liked);
                            }

                            return publicationViewDto;
                        }
                )
                .toList();
    }
}
