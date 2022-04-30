package co.com.usta.renttyj.models.daos;

import co.com.usta.renttyj.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, Long> {
}
