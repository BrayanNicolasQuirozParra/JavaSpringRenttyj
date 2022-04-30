package co.com.usta.renttyj.controllers;

import co.com.usta.renttyj.entity.Loan;
import co.com.usta.renttyj.models.services.loan.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class LoanController {

    final LoanService loanService;
    private Loan loanObject;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
        initObject();
    }

    @GetMapping("/admin-loans")
    public String getAllLoans(Model model) {
        model.addAttribute("title", "Administrar Préstamos");
        model.addAttribute("titlePage", "Préstamos");
        model.addAttribute("loans", loanService.findAll());
        return "loan/admin-loans";
    }

    @GetMapping("create-loan")
    public String createLoan(Model model) {
        model.addAttribute("title", "Crear Préstamo");
        model.addAttribute("titlePage", "Préstamos");
        model.addAttribute("loanObject", new Loan());
        return "loan/save-loan";
    }

    @PostMapping("save-loan")
    public String saveLoan(@Valid Loan loan) {
        setParameters(loan);
        loanService.save(loanObject);
        initObject();
        return "redirect:/admin-loans";
    }

    @RequestMapping("/delete-loan/{id}")
    public String delete(@PathVariable Long id) {
        loanService.delete(id);
        return "redirect:/admin-loans";
    }

    @RequestMapping("/update-loan/{id}")
    public String update(@PathVariable Long id, Model model) throws Exception {
        loanObject = loanService.findById(id);
        model.addAttribute("loanObject", loanObject);
        model.addAttribute("title", "Actualizar Préstamo");
        model.addAttribute("titlePage", "Préstamos");
        return "loan/save-loan";
    }

    private void setParameters(Loan loan) {
        loanObject.setTimeDays(loan.getTimeDays());
        loanObject.setMaxDays(loan.getMaxDays());
        loanObject.setDailyLoans(loan.getDailyLoans());
        loanObject.setPenaltyFee(loan.getPenaltyFee());
    }

    private void initObject() {
        this.loanObject = new Loan();
    }

}
