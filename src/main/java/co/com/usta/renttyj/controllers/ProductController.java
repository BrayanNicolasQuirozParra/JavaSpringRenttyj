package co.com.usta.renttyj.controllers;

import co.com.usta.renttyj.entity.Product;
import co.com.usta.renttyj.models.services.category.CategoryService;
import co.com.usta.renttyj.models.services.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class ProductController {

    final ProductService productService;
    final CategoryService categoryService;
    private Product productObject;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        initObject();
    }

    @GetMapping("/admin-products")
    public String getAllProducts(Model model) {
        model.addAttribute("title", "Administrar Productos");
        model.addAttribute("titlePage", "Productos");
        model.addAttribute("products", productService.findAll());
        return "product/admin-products";
    }

    @GetMapping("create-product")
    public String createProduct(Model model) {
        model.addAttribute("title", "Crear Producto");
        model.addAttribute("titlePage", "Productos");
        model.addAttribute("productObject", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/save-product";
    }

    @PostMapping("save-product")
    public String saveProduct(@Valid Product product) {
        setParameters(product);
        productService.save(productObject);
        initObject();
        return "redirect:/admin-products";
    }

    @RequestMapping("/delete-product/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin-products";
    }

    @RequestMapping("/update-product/{id}")
    public String update(@PathVariable Long id, Model model) throws Exception {
        productObject = productService.findById(id);
        model.addAttribute("productObject", productObject);
        model.addAttribute("title", "Actualizar Producto");
        model.addAttribute("titlePage", "Productos");
        model.addAttribute("categories", categoryService.findAll());
        return "product/save-product";
    }

    private void setParameters(Product product) {
        productObject.setProductName(product.getProductName());
        productObject.setSpecifications(product.getSpecifications());
        productObject.setPrice(product.getPrice());
        productObject.setStock(product.getStock());
        productObject.setCategory(product.getCategory());
    }

    private void initObject() {
        this.productObject = new Product();
    }

}
