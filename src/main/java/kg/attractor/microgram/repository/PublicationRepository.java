package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {

    List<Publication> findPublicationsByOwner_Email(String ownerEmail);

    @Query("""
     select p from Publication p where p.owner.email 
             in (select s.subscriber.email from Subscription s where s.follower.email = :email)
             order by p.createdDate desc 
     """)
    List<Publication> findPublicationsFromSubscriptions(String email);
}
