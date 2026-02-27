package kg.attractor.microgram.service.impl;

import jakarta.transaction.Transactional;
import kg.attractor.microgram.model.Subscription;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.repository.SubscriptionRepository;
import kg.attractor.microgram.service.SubscriptionService;
import kg.attractor.microgram.service.UserService;
import kg.attractor.microgram.util.ConvertHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository repository;
    private final UserService userService;


    @Override
    public boolean toggleSubscription(String followerEmail, String subscriberEmail) {

        log.info("follower {}", followerEmail);
        log.info("subscriber {}", subscriberEmail);

        boolean exists = repository
                .existsByFollower_EmailAndSubscriber_Email(followerEmail, subscriberEmail);

        User follower = userService.getUserByEmail(followerEmail);
        User subscriber = userService.getUserByEmail(subscriberEmail);

        if (exists) {
            repository.deleteByFollower_EmailAndSubscriber_Email(followerEmail, subscriberEmail);

            follower.setSubscriptions(follower.getSubscriptions() - 1);
            subscriber.setFollowers(subscriber.getFollowers() - 1);

            log.info("followerCount {}", follower.getSubscriptions());
            log.info("subscriberCount {}", subscriber.getSubscriptions());

            userService.save(follower);
            userService.save(subscriber);

            return false;
        }

        Subscription subscription = ConvertHelper.convertToSubscription(follower, subscriber);

        repository.save(subscription);

        follower.setSubscriptions(follower.getSubscriptions() + 1);
        subscriber.setFollowers(subscriber.getFollowers() + 1);

        userService.save(follower);
        userService.save(subscriber);

        return true;
    }

    @Override
    public boolean isSubscribed(String followerEmail, String subscriberEmail) {
        return repository
                .existsByFollower_EmailAndSubscriber_Email(followerEmail, subscriberEmail);
    }
}
