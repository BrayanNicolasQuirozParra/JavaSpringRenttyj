package co.com.usta.renttyj.controllers;

import co.com.usta.renttyj.entity.UserAuth;
import co.com.usta.renttyj.models.services.role.RoleService;
import co.com.usta.renttyj.models.services.userauth.UserAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserAuthController {

    final UserAuthService userService;
    final RoleService roleService;
    private UserAuth userObject;

    public UserAuthController(UserAuthService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        initObject();
    }

    @GetMapping("/admin-users")
    public String getAllUsers(Model model) {
        model.addAttribute("title", "Administrar Usuarios");
        model.addAttribute("titlePage", "Usuarios");
        model.addAttribute("users", userService.findAll());
        return "user/admin-users";
    }

    @GetMapping("create-user")
    public String createUser(Model model) {
        model.addAttribute("title", "Crear Usuario");
        model.addAttribute("titlePage", "Usuarios");
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("userObject", new UserAuth());
        return "user/save-user";
    }

    @PostMapping("save-user")
    public String saveUser(@Valid UserAuth user) {
        setParameters(user);
        userService.save(userObject);
        initObject();
        return "redirect:/admin-users";
    }

    @RequestMapping("/delete-user/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin-users";
    }

    @RequestMapping("/update-user/{id}")
    public String update(@PathVariable Long id, Model model) throws Exception {
        userObject = userService.findById(id);
        model.addAttribute("userObject", userObject);
        model.addAttribute("title", "Actualizar Usuario");
        model.addAttribute("titlePage", "Usuarios");
        model.addAttribute("roles", roleService.findAll());
        return "user/save-user";
    }

    private void setParameters(UserAuth user) {
        userObject.setUsername(user.getUsername());
        userObject.setEmail(user.getEmail());
        userObject.setPassword(user.getPassword());
        userObject.setRole(user.getRole());
    }

    private void initObject() {
        this.userObject = new UserAuth();
    }
}
