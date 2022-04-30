package co.com.usta.renttyj.models.daos;

import co.com.usta.renttyj.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long> {
}
