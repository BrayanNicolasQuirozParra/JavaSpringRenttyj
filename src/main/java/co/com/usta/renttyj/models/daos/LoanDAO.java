package co.com.usta.renttyj.models.daos;

import co.com.usta.renttyj.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDAO extends JpaRepository<Loan, Long> {
}
