package bim444.bootcamper.basebootcamp.techcareer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TechcareerRepository extends JpaRepository<Techcareer, UUID> {
    Optional<Techcareer> findByName(String name);
}
