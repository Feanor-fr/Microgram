package kg.attractor.microgram.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PUBLICATIONS")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate createdDate;

    @Column(nullable = false)
    private Integer likes;

    @Column(nullable = false)
    private Integer comments;

    @ManyToOne
    @JoinColumn(name = "OWNER", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "publication")
    private List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "publication")
    private List<Comment> commentList = new ArrayList<>();
}
