package co.com.usta.renttyj.models.services.category;

import co.com.usta.renttyj.entity.Category;
import co.com.usta.renttyj.handler.exceptions.CustomException;
import co.com.usta.renttyj.models.daos.CategoryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    final CategoryDAO repository;

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Category findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(
                        String.format("La categoría con código %s no fue encontrada", id)));
    }
}
