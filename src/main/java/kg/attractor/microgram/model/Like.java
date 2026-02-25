package kg.attractor.microgram.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "OWNER_LIKE", nullable = false)
    private User ownerLike;

    @ManyToOne
    @JoinColumn(name = "PUBLICATION_ID", nullable = false)
    private Publication publication;
}
