package edu.hm.shareit.models.mediums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book extends Medium {
    private String isbn = null;
    private String author = null;

    public Book(String title) {
        super(title);
    }

    @JsonCreator
    public Book(@JsonProperty("title") String title,
                @JsonProperty("isbn") String isbn,
                @JsonProperty("author") String author) {
        super(title);
        this.isbn = isbn;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        return author != null ? author.equals(book.author) : book.author == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + "\'" +
                ", author='" + author + "\'" +
                "}";
    }
}
