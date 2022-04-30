package co.com.usta.renttyj.models.daos;

import co.com.usta.renttyj.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDAO extends JpaRepository<Company, Long> {
}
