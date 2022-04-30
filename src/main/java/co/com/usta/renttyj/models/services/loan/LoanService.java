package co.com.usta.renttyj.models.services.loan;

import co.com.usta.renttyj.entity.Loan;

import java.util.List;

public interface LoanService {

    Loan save(Loan loan);

    List<Loan> findAll();

    void delete(Long id);

    Loan findById(Long id) throws Exception;
}
