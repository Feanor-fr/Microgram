package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.exception.PublicationNotFoundException;
import kg.attractor.microgram.model.Like;
import kg.attractor.microgram.model.Publication;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.repository.LikeRepository;
import kg.attractor.microgram.repository.PublicationRepository;
import kg.attractor.microgram.service.LikeService;
import kg.attractor.microgram.util.ConvertHelper;
import kg.attractor.microgram.util.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository repository;
    private final PublicationRepository publicationService;
    private final UserHelper userHelper;

    @Override
    public void create(Integer publicationId) {

        User authUser = userHelper.getAuthUser();
        Publication publication = publicationService.findById(publicationId)
                .orElseThrow(PublicationNotFoundException::new);

        Boolean exists = repository
                .existsByOwnerLike_EmailAndPublication_Id(authUser.getEmail(), publicationId);

        if (exists) {
            return;
        }

        Like like = ConvertHelper.convertToLike(authUser, publication);

        repository.saveAndFlush(like);

        publication.setLikes(publication.getLikes() + 1);
        publicationService.save(publication);
    }

    @Override
    public Map<String, Object> createAndReturn(Integer publicationId) {

        User authUser = userHelper.getAuthUser();
        Publication publication = publicationService.findById(publicationId)
                .orElseThrow(PublicationNotFoundException::new);

        Map<String, Object> response = new HashMap<>();

        Boolean exists = repository
                .existsByOwnerLike_EmailAndPublication_Id(authUser.getEmail(), publicationId);

        if (exists) {
            response.put("alreadyLiked", true);
            response.put("likesCount", publication.getLikes());
            return response;
        }

        Like like = Like.builder()
                .ownerLike(authUser)
                .publication(publication)
                .build();

        repository.saveAndFlush(like);

        publication.setLikes(publication.getLikes() + 1);
        publicationService.save(publication);

        response.put("alreadyLiked", false);
        response.put("likesCount", publication.getLikes());

        return response;
    }

    @Override
    public void delete(Integer publicationId) {

        User authUser = userHelper.getAuthUser();

        Like like = repository
                .findByOwnerLike_EmailAndPublication_Id(authUser.getEmail(), publicationId);

        if (like == null) {
            return;
        }

        Publication publication = like.getPublication();

        publication.setLikes(publication.getLikes() - 1);
        publicationService.save(publication);

        repository.delete(like);
    }

    @Override
    public int getLikesCount(Integer publicationId) {
        return publicationService.findById(publicationId)
                .orElseThrow(PublicationNotFoundException::new)
                .getLikes();
    }

    @Override
    public Boolean existsByOwnerLike_EmailAndPublication_Id(String email, Integer id) {
      return   repository.existsByOwnerLike_EmailAndPublication_Id(email, id);
    }
}
