package co.com.usta.renttyj.models.daos;

import co.com.usta.renttyj.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Long> {
}
