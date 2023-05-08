package server.bookmanagement.domain.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.bookmanagement.domain.loan.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {
}
