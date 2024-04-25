package bim444.bootcamper.basebootcamp.techcareer;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TechcareerRepository extends JpaRepository<Techcareer, UUID> {
    @Transactional
    List<Techcareer> findByName(String name);
}
