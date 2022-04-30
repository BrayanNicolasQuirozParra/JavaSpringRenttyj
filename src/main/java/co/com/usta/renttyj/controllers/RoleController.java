package co.com.usta.renttyj.controllers;

import co.com.usta.renttyj.entity.Role;
import co.com.usta.renttyj.models.services.role.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RoleController {

    final RoleService roleService;
    private Role roleObject;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
        initObject();
    }

    @GetMapping("/admin-roles")
    public String getAllRoles(Model model) {
        model.addAttribute("title", "Administrar Roles");
        model.addAttribute("titlePage", "Roles");
        model.addAttribute("roles", roleService.findAll());
        return "role/admin-roles";
    }

    @GetMapping("create-role")
    public String createRole(Model model) {
        model.addAttribute("title", "Crear Rol");
        model.addAttribute("titlePage", "Roles");
        model.addAttribute("roleObject", new Role());
        return "role/save-role";
    }

    @PostMapping("save-role")
    public String saveRole(@Valid Role role) {
        setParameters(role);
        roleService.save(roleObject);
        initObject();
        return "redirect:/admin-roles";
    }

    @RequestMapping("/delete-role/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return "redirect:/admin-roles";
    }

    @RequestMapping("/update-role/{id}")
    public String update(@PathVariable Long id, Model model) throws Exception {
        roleObject = roleService.findById(id);
        model.addAttribute("roleObject", roleObject);
        model.addAttribute("title", "Actualizar Rol");
        model.addAttribute("titlePage", "Roles");
        return "role/save-role";
    }

    private void setParameters(Role role) {
        roleObject.setName(role.getName());
    }

    private void initObject() {
        this.roleObject = new Role();
    }
}
