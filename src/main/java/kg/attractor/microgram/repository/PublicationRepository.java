package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {
}
