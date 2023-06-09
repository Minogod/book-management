package server.bookmanagement.domain.loan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import server.bookmanagement.domain.library.inventory.entity.LibraryInventory;
import server.bookmanagement.domain.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "loan_history")
@Getter @Setter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime loanedAt;
    private LocalDateTime returnedAt;
    @Enumerated(value = EnumType.STRING)
    private LoanStats loanStats = LoanStats.대여중;
    // 회원이 삭제될때, 책이 삭제될때, 도서관이 삭제될때, 도서관이 삭제될때
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference(value = "member_loans")
//    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Member member;
    @ManyToOne
    @JsonBackReference(value = "loan_libraryInventory")
    @JoinColumn(name = "library_inventory_id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private LibraryInventory libraryInventory;

    public enum LoanStats {
        대여중,
        반납완료
    }

}
