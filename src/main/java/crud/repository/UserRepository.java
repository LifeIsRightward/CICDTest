package crud.repository;

import crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    Optional<User> findById(Long id);
    User findByEmail(String email);
    boolean existsByname(String name);
    User findByName(String username);

    User findByLoginId(String username);
}
