package bim444.bootcamper.basebootcamp.coderspace;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoderspaceRepository extends JpaRepository<Coderspace, String> {
    Optional<Coderspace> findByName(String name);
}
