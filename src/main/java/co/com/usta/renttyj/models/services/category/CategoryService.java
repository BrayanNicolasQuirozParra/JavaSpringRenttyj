package co.com.usta.renttyj.models.services.category;

import co.com.usta.renttyj.entity.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();

    void delete(Long id);

    Category findById(Long id) throws Exception;
}
