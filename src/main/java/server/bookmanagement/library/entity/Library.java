package server.bookmanagement.library.entity;

import server.bookmanagement.util.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class Library extends BaseEntity {
    private String name;
    private String img_url;
    @OneToMany(mappedBy = "library", cascade = CascadeType.REMOVE)
    private List<LibraryBook> libraryBooks;
}
