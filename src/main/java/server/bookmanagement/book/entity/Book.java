package server.bookmanagement.book.entity;

import server.bookmanagement.util.BaseEntity;

import javax.persistence.*;

@Entity
public class Book extends BaseEntity {
    private String name;
    private String writer;
    private String img_url;
    private String publisher;

}
