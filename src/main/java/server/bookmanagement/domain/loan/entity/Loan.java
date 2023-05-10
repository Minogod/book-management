package server.bookmanagement.domain.loan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "library_inventory_id")
    private LibraryInventory libraryInventory;

    public enum LoanStats {
        대여중,
        반납완료
    }

}
