package server.bookmanagement.domain.loan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import server.bookmanagement.domain.loan.dto.LoanDto;
import server.bookmanagement.domain.loan.entity.Loan;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanMapper {
    Loan LoanPostToLoan(LoanDto.Post post);
    LoanDto.Response LoanToLoanResponse(Loan loan);
}
