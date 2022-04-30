package co.com.usta.renttyj.models.services.product;

import co.com.usta.renttyj.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    void delete(Long id);

    Product findById(Long id) throws Exception;
}
