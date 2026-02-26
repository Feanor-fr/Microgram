package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    Boolean existsByFollower_EmailAndSubscriber_Email(String followerEmail, String subscriberEmail);

    void deleteByFollower_EmailAndSubscriber_Email(String followerEmail, String subscriberEmail);

    
}
