package krzypio.forge.backend.authentication;

import krzypio.forge.backend.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u " +
            "where lower(u.userName) like lower(concat('%', :searchTerm, '%')) ")
    List<User> search(@Param("searchTerm") String searchTerm);

    Optional<User> findByUserName(String userName);
}
