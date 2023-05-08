package server.bookmanagement.domain.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.bookmanagement.domain.library.inventory.dto.LibraryInventoryDto;
import server.bookmanagement.domain.loan.entity.Loan;
import server.bookmanagement.domain.member.dto.MemberDto;

import java.time.LocalDateTime;

public class LoanDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private long memberId;
        private long libraryInventoryId;
    }
    @Getter
    @AllArgsConstructor
    public static class Response{
        private long id;
        private MemberDto.Response member;
        private LibraryInventoryDto.Response libraryInventory;
        private LocalDateTime loanedAt;
        private LocalDateTime returnedAt;
        private Loan.LoanStats loanStats;
    }
}
