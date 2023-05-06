package server.bookmanagement.library.inventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import server.bookmanagement.book.entity.Book;
import server.bookmanagement.library.library.entity.Library;
import server.bookmanagement.util.BaseEntity;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class LibraryInventory extends BaseEntity {
    private int totalQuantity;
    @ColumnDefault(value = "0")
    private int loanQuantity;
    @Enumerated(value = EnumType.STRING)
    private LoanStatus loanStatus = LoanStatus.대여가능;
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
