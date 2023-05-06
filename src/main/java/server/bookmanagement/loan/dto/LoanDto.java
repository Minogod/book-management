package server.bookmanagement.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.bookmanagement.library.inventory.dto.LibraryInventoryDto;
import server.bookmanagement.loan.entity.Loan;
import server.bookmanagement.member.dto.MemberDto;

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
        private LocalDateTime returnAt;
        private Loan.LoanStats loanStats;
    }
}
