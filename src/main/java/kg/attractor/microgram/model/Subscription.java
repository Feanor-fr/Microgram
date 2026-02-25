package kg.attractor.microgram.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "SUBSCRIPTIONS")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "NEW_SUBSCRIPT", nullable = false)
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "NEW_FOLLOWER", nullable = false)
    private User follower;
}
