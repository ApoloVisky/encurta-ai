package dev.apolo.EncurtaAI.Links;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByUrlEncurtada(String urlEncurtada);
}
