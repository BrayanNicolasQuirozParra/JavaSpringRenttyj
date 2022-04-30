package co.com.usta.renttyj.controllers;

import co.com.usta.renttyj.entity.Category;
import co.com.usta.renttyj.models.services.category.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class CategoryController {

    final CategoryService categoryService;
    private Category categoryObject;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
        initObject();
    }

    @GetMapping("/admin-categories")
    public String getAllCategories(Model model) {
        model.addAttribute("title", "Administrar Categorías");
        model.addAttribute("titlePage", "Categorías");
        model.addAttribute("categories", categoryService.findAll());
        return "category/admin-categories";
    }

    @GetMapping("create-category")
    public String createCategory(Model model) {
        model.addAttribute("title", "Crear Categoría");
        model.addAttribute("titlePage", "Categorías");
        model.addAttribute("categoryObject", new Category());
        return "category/save-category";
    }

    @PostMapping("save-category")
    public String saveCategory(@Valid Category category) {
        setParameters(category);
        categoryService.save(categoryObject);
        initObject();
        return "redirect:/admin-categories";
    }

    @RequestMapping("/delete-category/{id}")
    public String delete(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/admin-categories";
    }

    @RequestMapping("/update-category/{id}")
    public String update(@PathVariable Long id, Model model) throws Exception {
        categoryObject = categoryService.findById(id);
        model.addAttribute("categoryObject", categoryObject);
        model.addAttribute("title", "Actualizar Categoría");
        model.addAttribute("titlePage", "Categorías");
        return "category/save-category";
    }

    private void setParameters(Category category) {
        categoryObject.setNameCategory(category.getNameCategory());
    }

    private void initObject() {
        this.categoryObject = new Category();
    }
}
