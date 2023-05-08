package server.bookmanagement.loan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.bookmanagement.global.error.exception.BusinessLogicException;
import server.bookmanagement.global.error.exception.ExceptionCode;
import server.bookmanagement.loan.entity.Loan;
import server.bookmanagement.loan.repository.LoanRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;

    public Loan loanBook(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan returnBook(Loan loan) {
        loan.setReturnedAt(LocalDateTime.now());
        loan.setLoanStats(Loan.LoanStats.반납완료);
        return loanRepository.save(loan);
    }
    public Loan findById(long id) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        return optionalLoan.orElseThrow(()->new BusinessLogicException(ExceptionCode.LOAN_NOT_FOUND));
    }
}
