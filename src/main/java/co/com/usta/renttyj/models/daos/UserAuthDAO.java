package co.com.usta.renttyj.models.daos;

import co.com.usta.renttyj.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthDAO extends JpaRepository<UserAuth, Long> {

    Optional<UserAuth> findUserAuthByUsername(String username);
}
