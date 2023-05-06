package server.bookmanagement.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.bookmanagement.loan.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {
}
