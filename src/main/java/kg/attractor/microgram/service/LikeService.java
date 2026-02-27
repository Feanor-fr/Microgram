package kg.attractor.microgram.service;

import java.util.Map;

public interface LikeService {
    void create(Integer publicationId);

    Map<String, Object> createAndReturn(Integer publicationId);

    void delete(Integer publicationId);

    int getLikesCount(Integer publicationId);

    Boolean existsByOwnerLike_EmailAndPublication_Id(String email, Integer id);
}
