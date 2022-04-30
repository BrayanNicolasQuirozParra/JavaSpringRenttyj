package co.com.usta.renttyj.controllers;

import co.com.usta.renttyj.entity.Authority;
import co.com.usta.renttyj.models.services.authority.AuthorityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("authorityObject")
public class AuthorityController {

    final AuthorityService authorityService;
    private Authority authorityObject;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
        initObject();
    }

    @GetMapping("/admin-authorities")
    public String getAllAuthorities(Model model) {
        model.addAttribute("title", "Administrar Permisos");
        model.addAttribute("titlePage", "Permisos");
        model.addAttribute("authorities", authorityService.findAll());
        return "authority/admin-authorities";
    }

    @GetMapping("create-authority")
    public String createAuthority(Model model) {
        model.addAttribute("title", "Crear Permiso");
        model.addAttribute("titlePage", "Permisos");
        model.addAttribute("authorityObject", new Authority());
        return "authority/save-authority";
    }

    @PostMapping("save-authority")
    public String saveAuthority(@Valid Authority authority, SessionStatus status) {
        setParameters(authority);
        authorityService.save(authorityObject);
        status.setComplete();
        initObject();
        return "redirect:/admin-authorities";
    }

    @RequestMapping("/delete-authority/{id}")
    public String delete(@PathVariable Long id) {
        authorityService.delete(id);
        return "redirect:/admin-authorities";
    }

    @RequestMapping("/update-authority/{id}")
    public String update(@PathVariable Long id, Model model) throws Exception {
        authorityObject = authorityService.findById(id);
        model.addAttribute("authorityObject", authorityObject);
        model.addAttribute("title", "Actualizar Permiso");
        model.addAttribute("titlePage", "Permisos");
        return "authority/save-authority";
    }

    private void setParameters(Authority authority) {
        authorityObject.setName(authority.getName());
    }

    private void initObject() {
        this.authorityObject = new Authority();
    }

}
