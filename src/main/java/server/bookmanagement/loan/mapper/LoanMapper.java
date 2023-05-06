package server.bookmanagement.loan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import server.bookmanagement.loan.dto.LoanDto;
import server.bookmanagement.loan.entity.Loan;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanMapper {
    Loan LoanPostToLoan(LoanDto.Post post);
    LoanDto.Response LoanToLoanResponse(Loan loan);
}
