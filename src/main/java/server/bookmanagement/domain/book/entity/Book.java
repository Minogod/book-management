package server.bookmanagement.domain.book.entity;

import lombok.Getter;
import lombok.Setter;
import server.bookmanagement.util.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Book extends BaseEntity {
    private String name;
    private String writer;
    private String img_url;
    private String publisher;
}
