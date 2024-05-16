package bim444.bootcamper.basebootcamp.patika;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatikaRepository extends JpaRepository<Patika, String> {
    Optional<Patika> findByName(String name);
    Patika findByNameAndIsDeadFalse(String name);
    List<Patika> findByIsDead(Boolean isDead);

}
