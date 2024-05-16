package bim444.bootcamper.basebootcamp.techcareer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

public interface TechcareerRepository extends JpaRepository<Techcareer, String> {
    @Transactional
    Optional<Techcareer> findByName(String name);
}
