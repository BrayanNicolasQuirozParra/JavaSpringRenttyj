package co.com.usta.renttyj.models.services.loan;

import co.com.usta.renttyj.entity.Loan;
import co.com.usta.renttyj.handler.exceptions.CustomException;
import co.com.usta.renttyj.models.daos.LoanDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    final LoanDAO repository;

    @Override
    public Loan save(Loan loan) {
        if (loan.getId() == null) {
            loan.setAmount(0d);
            loan.setState("ENVIADO");
            loan.setDeliveryDate(LocalDateTime.now().plusDays(loan.getTimeDays()));
        }
        return repository.save(loan);
    }

    @Override
    public List<Loan> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Loan findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(
                        String.format("El préstamo con código %s no fue encontrado", id)));
    }
}
