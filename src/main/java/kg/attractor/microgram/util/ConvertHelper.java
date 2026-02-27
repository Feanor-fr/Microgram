package kg.attractor.microgram.util;

import kg.attractor.microgram.dto.*;
import kg.attractor.microgram.model.*;

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
                .owner(publication.getOwner().getEmail())
                .build();
    }

    public static Subscription convertToSubscription(User follower, User subscriber){

        return Subscription.builder()
                .follower(follower)
                .subscriber(subscriber)
                .build();

    }

    public static Comment convertToComment(CommentCreateDto commentCreateDto, Publication publication, User user){

        return Comment.builder()
                .commentText(commentCreateDto.getCommentText())
                .createdDate(LocalDate.now())
                .ownerComment(user)
                .publication(publication)
                .build();
    }

    public static CommentViewDto convertToCommentViewDto(Comment comment){

        return CommentViewDto.builder()
                .id(comment.getId())
                .commentText(comment.getCommentText())
                .createdDate(comment.getCreatedDate())
                .ownerLogin(comment.getOwnerComment().getLogin())
                .build();
    }

    public static Like convertToLike(User user, Publication publication ){

        return Like.builder()
                .ownerLike(user)
                .publication(publication)
                .build();
    }
}
