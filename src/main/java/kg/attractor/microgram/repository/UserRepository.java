package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByEmail(String email);

    Boolean existsByLogin(String login);

    List<User> findUsersByEmailContaining(String email);

    List<User> findUsersByLoginContainingIgnoreCase(String login);

    List<User> findUsersByUserNameContainingIgnoreCase(String userName);
}
