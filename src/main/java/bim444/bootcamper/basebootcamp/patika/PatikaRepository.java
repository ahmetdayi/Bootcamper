package bim444.bootcamper.basebootcamp.patika;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatikaRepository extends JpaRepository<Patika, UUID> {
    Optional<Patika> findByName(String name);
    Patika findByNameAndIsDeadFalse(String name);
    List<Patika> findByIsDead(Boolean isDead);
}
