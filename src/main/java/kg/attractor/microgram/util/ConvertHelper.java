package kg.attractor.microgram.util;

import kg.attractor.microgram.dto.PublicationCreateDto;
import kg.attractor.microgram.dto.PublicationViewDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.dto.UserDtoForView;
import kg.attractor.microgram.model.Publication;
import kg.attractor.microgram.model.Subscription;
import kg.attractor.microgram.model.User;

import java.time.LocalDate;

public class ConvertHelper {

    private ConvertHelper() {
    }

    public static User convertTouser(UserDto userDto){

        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .login(userDto.getLogin())
                .userName(userDto.getUserName())
                .description(userDto.getDescription())
                .followers(0)
                .subscriptions(0)
                .enabled(true)
                .build();
    }

    public static UserDtoForView convertToUserDtoForView(User user, Integer publicationCount){

        return UserDtoForView.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .login(user.getLogin())
                .userName(user.getUserName())
                .description(user.getDescription())
                .followers(user.getFollowers())
                .subscriptions(user.getSubscriptions())
                .publicationsCount(publicationCount)
                .avatar(user.getAvatar())
                .build();
    }

    public static Publication convertToPublication(PublicationCreateDto publicationCreateDto, String fileMane, User owner){

        return Publication.builder()
                .photo(fileMane)
                .description(publicationCreateDto.getDescription())
                .createdDate(LocalDate.now())
                .likes(0)
                .comments(0)
                .owner(owner)
                .build();
    }


    public static PublicationViewDto convertToPublicationViewDto(Publication publication){

        return PublicationViewDto.builder()
                .id(publication.getId())
                .photo(publication.getPhoto())
                .description(publication.getDescription())
                .createdDate(publication.getCreatedDate())
                .likes(publication.getLikes())
                .comments(publication.getComments())
                .owner(publication.getOwner().getLogin())
                .build();
    }

    public static Subscription convertToSubscription(User follower, User subscriber){

        return Subscription.builder()
                .follower(follower)
                .subscriber(subscriber)
                .build();

    }
}
