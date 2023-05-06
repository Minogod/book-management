package server.bookmanagement.loan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.bookmanagement.loan.entity.Loan;
import server.bookmanagement.loan.repository.LoanRepository;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;

    public Loan loanBook(Loan loan) {
        return loanRepository.save(loan);
    }
}
