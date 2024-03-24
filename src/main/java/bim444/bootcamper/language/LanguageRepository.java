package bim444.bootcamper.language;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LanguageRepository extends JpaRepository<Language, UUID> {
    Optional<Language> findByName(String name);
}
