package co.com.usta.renttyj.controllers;

import co.com.usta.renttyj.entity.Company;
import co.com.usta.renttyj.models.services.company.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class CompanyController {

    final CompanyService companyService;
    private Company companyObject;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
        initObject();
    }

    @GetMapping("/admin-companies")
    public String getAllCompanies(Model model) {
        model.addAttribute("title", "Administrar Empresas");
        model.addAttribute("titlePage", "Empresas");
        model.addAttribute("companies", companyService.findAll());
        return "company/admin-companies";
    }

    @GetMapping("create-company")
    public String createCompany(Model model) {
        model.addAttribute("title", "Crear Empresa");
        model.addAttribute("titlePage", "Empresas");
        model.addAttribute("companyObject", new Company());
        return "company/save-company";
    }

    @PostMapping("save-company")
    public String saveCompany(@Valid Company company) {
        setParameters(company);
        companyService.save(companyObject);
        initObject();
        return "redirect:/admin-companies";
    }

    @RequestMapping("/delete-company/{id}")
    public String delete(@PathVariable Long id) {
        companyService.delete(id);
        return "redirect:/admin-companies";
    }

    @RequestMapping("/update-company/{id}")
    public String update(@PathVariable Long id, Model model) throws Exception {
        companyObject = companyService.findById(id);
        model.addAttribute("companyObject", companyObject);
        model.addAttribute("title", "Actualizar Empresa");
        model.addAttribute("titlePage", "Empresas");
        return "company/save-company";
    }

    private void setParameters(Company company) {
        companyObject.setNit(company.getNit());
        companyObject.setName(company.getName());
        companyObject.setAddress(company.getAddress());
        companyObject.setLocation(company.getLocation());
    }

    private void initObject() {
        this.companyObject = new Company();
    }

}
