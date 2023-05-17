package server.bookmanagement.domain.library.inventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import server.bookmanagement.domain.book.entity.Book;
import server.bookmanagement.domain.library.library.entity.Library;
import server.bookmanagement.domain.loan.entity.Loan;
import server.bookmanagement.util.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class LibraryInventory extends BaseEntity {
    private int totalQuantity; // 5
    @ColumnDefault(value = "0")
    private int loanQuantity; // 1 , 2  ... 5
    @Enumerated(value = EnumType.STRING)
    private LoanStatus loanStatus = LoanStatus.대여가능; // 모두 대여중
    private boolean isDeleted = false;
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference(value = "book_libraryInventories")
//    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Book book;
    @ManyToOne
    @JoinColumn(name = "library_id")
    @JsonBackReference(value = "library_libraryInventories")
//    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Library library;

    @OneToMany(mappedBy = "libraryInventory", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "loan_libraryInventory")
    private List<Loan> loans = new ArrayList<>();

    public enum LoanStatus{
        대여가능, 모두대여중
    }
}
