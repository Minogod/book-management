package server.bookmanagement.loan.history.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import server.bookmanagement.book.entity.Book;
import server.bookmanagement.member.entity.Member;
import server.bookmanagement.util.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class LoanHistory extends BaseEntity {
    private LocalDateTime loanedAt;
    private LocalDateTime returnAt;
    private LoanStats loanStats;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public enum LoanStats {
        대여중,
        반납완료
    }

}
