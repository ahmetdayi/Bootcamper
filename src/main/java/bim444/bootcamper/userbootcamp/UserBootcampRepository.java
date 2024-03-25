package bim444.bootcamper.userbootcamp;

import bim444.bootcamper.basebootcamp.coderspace.Coderspace;
import bim444.bootcamper.basebootcamp.patika.Patika;
import bim444.bootcamper.basebootcamp.techcareer.Techcareer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserBootcampRepository extends JpaRepository<UserBootcamp, String> {
//
    @Query("SELECT ub.baseBootcamp FROM UserBootcamp ub WHERE TYPE(ub.baseBootcamp) = Patika AND ub.user.id = :userId")
    List<Patika> findPatikasByUserId(@Param("userId") String userId);

    @Query("SELECT ub.baseBootcamp FROM UserBootcamp ub WHERE TYPE(ub.baseBootcamp) = Techcareer AND ub.user.id = :userId")
    List<Techcareer> findTechcareersByUserId(@Param("userId") String userId);

    @Query("SELECT ub.baseBootcamp FROM UserBootcamp ub WHERE TYPE(ub.baseBootcamp) = Coderspace AND ub.user.id = :userId")
    List<Coderspace> findCoderspaceByUserId(@Param("userId") String userId);

//    @EntityGraph(attributePaths = {"user","baseBootcamp"})
    List<UserBootcamp> findAll();


    List<UserBootcamp> findByUser_Id(String userId);
}
