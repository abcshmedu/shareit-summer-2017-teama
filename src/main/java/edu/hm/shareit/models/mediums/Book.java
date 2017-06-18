package edu.hm.shareit.models.mediums;

import javax.persistence.*;

/**
 * Represents a book with a unique isbn.
 * The isbn is unique, title and author can exist more than once.
 */
@Entity
@Table(name = "Book")
public class Book extends Medium {

    @Id
    @Column(name = "ISBN", length = 13)
    private String isbn;

    @Column(name = "Author")
    private String author;

    /**
     * The default constructor.
     */
    public Book() {
        this(null, null, null);
    }

    /**
     * The custom constructor.
     *
     * @param title  Title of the book.
     * @param isbn   Isbn number of the book.
     * @param author Author of the book.
     */
    public Book(String title,
                String isbn,
                String author) {
        setTitle(title);
        setIsbn(isbn);
        setAuthor(author);
    }

    /**
     * Getter for isbn number.
     *
     * @return The isbn of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Getter for the author.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter for isbn number.
     *
     * @param isbn The isbn of the book.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Setter for the author.
     *
     * @param author The author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Book book = (Book) o;

        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) {
            return false;
        }
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
        return "Book{"
                + "isbn='" + isbn + "\'"
                + ", author='" + author + "\'"
                + "}";
    }
}
