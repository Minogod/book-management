package server.bookmanagement.domain.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import server.bookmanagement.domain.library.inventory.entity.LibraryInventory;
import server.bookmanagement.util.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Book extends BaseEntity {
    private String name;
    private String writer;
    private String publisher;
    private boolean isDeleted = false;
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
//    @JsonManagedReference(value = "book_libraryInventories")
    @JsonIgnoreProperties("book")
    private List<LibraryInventory> libraryInventories = new ArrayList<>();
}
