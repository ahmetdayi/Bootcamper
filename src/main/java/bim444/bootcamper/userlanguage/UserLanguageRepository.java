package bim444.bootcamper.userlanguage;

import bim444.bootcamper.language.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLanguageRepository extends JpaRepository<UserLanguage, String> {

    List<UserLanguage> findByUser_Id(String userId);
    List<UserLanguage> findByLanguage(Language language);

}
