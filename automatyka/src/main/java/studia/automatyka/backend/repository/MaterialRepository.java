package studia.automatyka.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import studia.automatyka.backend.enitity.Material;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    @Query("select m from Material m " +
            "where lower(m.name) like lower(concat('%', :searchTerm, '%'))")
    List<Material> search(@Param("searchTerm") String searchTerm); //

}
