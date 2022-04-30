package co.com.usta.renttyj.models.daos;

import co.com.usta.renttyj.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDAO extends JpaRepository<Authority, Long> {
}
