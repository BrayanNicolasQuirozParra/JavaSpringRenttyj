package co.com.usta.renttyj.models.services.product;

import co.com.usta.renttyj.entity.Product;
import co.com.usta.renttyj.handler.exceptions.CustomException;
import co.com.usta.renttyj.models.daos.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final ProductDAO repository;

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setState("ACTIVO");
        }
        return repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(
                        String.format("El producto con código %s no fue encontrado", id)));
    }
}
