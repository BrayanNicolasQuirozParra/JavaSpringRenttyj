package co.com.usta.renttyj.models.daos;

import co.com.usta.renttyj.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Long> {
}
