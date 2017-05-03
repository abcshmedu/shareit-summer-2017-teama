package edu.hm.shareit.models.mediums;

public class Book extends Medium {
    private String isbn;
    private String author;

    public Book() {
        this(null, null, null);
    }

    public Book( String title,
                String isbn,
                String author) {
        setTitle(title);
        setIsbn(isbn);
        setAuthor(author);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public void setAuthor(String author){
        this.author = author;
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
