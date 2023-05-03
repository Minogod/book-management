package server.bookmanagement.library.entity;

import server.bookmanagement.book.entity.Book;
import server.bookmanagement.util.BaseEntity;

import javax.persistence.*;

@Entity(name = "map_library_book")
public class LibraryBook extends BaseEntity {
    private int totalQuantity;
    private int loanQuantity;
    @Enumerated(value = EnumType.STRING)
    private LoanStatus loanStatus;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    public enum LoanStatus{
        대여가능,
        모두대여중,
        대여불가능책
    }
}
