package escahya.core.pinjamanservice.repositories;

import escahya.core.pinjamanservice.entities.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Optional<Users> findByUsername(String username);
}
