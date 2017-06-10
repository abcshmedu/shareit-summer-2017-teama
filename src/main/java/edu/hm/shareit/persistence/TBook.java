package edu.hm.shareit.persistence;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TBook")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TBook implements Serializable {

    @Id
    @Column(name = "TBook_ISBN", length = 13)
    private String isbn;

    @Column(name = "TBook_Author")
    private String author;

    public TBook() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
