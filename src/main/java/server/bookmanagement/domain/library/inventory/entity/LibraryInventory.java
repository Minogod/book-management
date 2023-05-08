package server.bookmanagement.domain.library.inventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import server.bookmanagement.domain.book.entity.Book;
import server.bookmanagement.domain.library.library.entity.Library;
import server.bookmanagement.util.BaseEntity;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class LibraryInventory extends BaseEntity {
    private int totalQuantity; // 5
    @ColumnDefault(value = "0")
    private int loanQuantity; // 1 , 2  ... 5
    @Enumerated(value = EnumType.STRING)
    private LoanStatus loanStatus = LoanStatus.대여가능; // 모두 대여중
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference
    private Book book;
    @ManyToOne
    @JoinColumn(name = "library_id")
    @JsonBackReference
    private Library library;

    public enum LoanStatus{
        대여가능, 모두대여중, 대여불가능책
    }
}
