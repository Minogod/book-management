package server.bookmanagement.domain.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.bookmanagement.domain.loan.entity.Loan;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByLibraryInventoryId(long libraryInventoryId);
}
