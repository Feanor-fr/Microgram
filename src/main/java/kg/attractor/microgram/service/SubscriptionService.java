package kg.attractor.microgram.service;

public interface SubscriptionService {
    boolean toggleSubscription(String followerEmail, String subscriberEmail);

    boolean isSubscribed(String followerEmail, String subscriberEmail);

    ;
}
