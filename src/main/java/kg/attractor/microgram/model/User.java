package kg.attractor.microgram.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class User {

    @Id
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String description;

    private String avatar;

    @Column(nullable = false)
    private Integer followers;

    @Column(nullable = false)
    private Integer subscriptions;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "ROLE_NAME", nullable = false)
    private  Role role;

    @OneToMany(mappedBy = "owner")
    private List<Publication> publications = new ArrayList<>();

    @OneToMany(mappedBy = "ownerLike")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "ownerComment")
    private List<Comment> comments  = new ArrayList<>();

    @OneToMany(mappedBy = "subscriber")
    private List<Subscription> subscriptionList = new ArrayList<>();

    @OneToMany(mappedBy = "follower")
    private List<Subscription> followerList = new ArrayList<>();

}
