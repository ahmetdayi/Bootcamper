package bim444.bootcamper.language;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, String> {
    Optional<Language> findByName(String name);
    List<Language> findByIdIn(List<String> userIdIn);
}
