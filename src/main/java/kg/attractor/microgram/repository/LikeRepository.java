package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

    Boolean existsByOwnerLike_EmailAndPublication_Id(String email, Integer publicationId);

    Like findByOwnerLike_EmailAndPublication_Id(String email, Integer publicationId);
}
