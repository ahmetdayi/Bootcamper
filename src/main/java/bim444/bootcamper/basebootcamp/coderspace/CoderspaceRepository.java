package bim444.bootcamper.basebootcamp.coderspace;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CoderspaceRepository extends JpaRepository<Coderspace, UUID> {
    Optional<Coderspace> findByName(String name);
}
