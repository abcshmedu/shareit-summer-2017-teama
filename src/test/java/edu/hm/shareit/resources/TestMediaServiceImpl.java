package edu.hm.shareit.resources;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;


public class TestMediaServiceImpl {
    MediaService mediaService = new MediaServiceImpl();

    @Test
    public void testAddBookWithAuthorAsNull() {
        MediaServiceResult result = mediaService.addBook(new Book("title", "isbn", null));
        assertEquals(MediaServiceResult.PARAMETER_MISSING, result);
    }

    @Test
    public void testAddBookWithEmptyAuthor() {
        MediaServiceResult result = mediaService.addBook(new Book("title", "isbn", ""));
        assertEquals(MediaServiceResult.PARAMETER_MISSING, result);
    }

    @Test
    public void testAddBookWithEmptyTitle() {
        MediaServiceResult result = mediaService.addBook(new Book("", "isbn", "author"));
        assertEquals(MediaServiceResult.PARAMETER_MISSING, result);
    }

    @Test
    public void testAddBookWithEmptyIsbn() {
        MediaServiceResult result = mediaService.addBook(new Book("title", "", "author"));
        assertEquals(MediaServiceResult.PARAMETER_MISSING, result);
    }

    @Test
    public void testAddBookWithInvalidISBN() {
        MediaServiceResult result = mediaService.addBook(new Book("title", "isbn", "author"));
        assertEquals(MediaServiceResult.INVALID_ISBN, result);
        result = mediaService.addBook(new Book("title", "1234567890abc", "author"));
        assertEquals(MediaServiceResult.INVALID_ISBN, result);
    }

    @Test
    public void testAddBookWithDuplicateIsbn() {
        mediaService.addBook(new Book("title", "1234567890123", "author"));
        MediaServiceResult result = mediaService.addBook(new Book("title2", "1234567890123", "author2"));
        assertEquals(MediaServiceResult.DUPLICATE_ISBN, result);
    }

    @Test
    public void testAddBookWithValidBook() {
        MediaServiceResult result = mediaService.addBook(new Book("title2", "1234567890123", "author2"));
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void testAddDiscWithNullValues() {
        MediaServiceResult result = mediaService.addDisc(new Disc(null, null, null, 0));
        assertEquals(MediaServiceResult.PARAMETER_MISSING, result);
    }

    @Test
    public void testAddDiscWithEmptyBarcode() {
        MediaServiceResult result = mediaService.addDisc(new Disc("title", "", "director", 0));
        assertEquals(MediaServiceResult.PARAMETER_MISSING, result);
    }

    @Test
    public void testAddDiscWithInvalidBarcode() {
        MediaServiceResult result = mediaService.addDisc(new Disc("title", "1234567890abc", "director", 0));
        assertEquals(MediaServiceResult.INVALID_BARCODE, result);
        result = mediaService.addDisc(new Disc("title", "123", "director", 0));
        assertEquals(MediaServiceResult.INVALID_BARCODE, result);
    }

    @Test
    public void testAddDiscDuplicateDisc() {
        mediaService.addDisc(new Disc("title2", "1234567890123", "author2", 0));
        MediaServiceResult result = mediaService.addDisc(new Disc("title2", "1234567890123", "author2", 0));
        assertEquals(MediaServiceResult.DUPLICATE_DISC, result);
    }

    @Test
    public void testAddDiscWithValidDisc() {
        MediaServiceResult result = mediaService.addDisc(new Disc("title2", "1234567890123", "author2", 0));
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void testUpdateBookWithNonMatchingIsbn() {
        Book book = new Book("title", "1234567890123", "author");
        mediaService.addBook(book);
        MediaServiceResult result = mediaService.updateBook(book, "3124567890123");
        assertEquals(MediaServiceResult.ISBN_DOES_NOT_MATCH, result);
    }

    @Test
    public void testUpdateBookWithIsbnNotFound() {
        Book book = new Book("title", "1234567890123", "author");
        MediaServiceResult result = mediaService.updateBook(book, "1234567890123");
        assertEquals(MediaServiceResult.ISBN_NOT_FOUND, result);
    }

    @Test
    public void testUpdateBookWithValidBook() {
        Book bookOld = new Book("title", "1234567890123", "author");
        Book newBook = new Book("titleNew", "1234567890123", "newAuthor");
        mediaService.addBook(bookOld);
        MediaServiceResult result = mediaService.updateBook(newBook, "1234567890123");
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void testUpdateBookWithEmptyBook() {
        Book bookOld = new Book("title", "1234567890123", "author");
        Book newBook = new Book(null, null, null);
        mediaService.addBook(bookOld);
        MediaServiceResult result = mediaService.updateBook(newBook, "1234567890123");
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }


    @Test
    public void testUpdateDiscWithNonMatchingBarcode() {
        Disc disc = new Disc("title", "1234567890123", "author", 0);
        mediaService.addDisc(disc);
        MediaServiceResult result = mediaService.updateDisc(disc, "3124567890123");
        assertEquals(MediaServiceResult.DISC_DOES_NOT_MATCH, result);
    }

    @Test
    public void testUpdateDiscWithBarcodeNotFound() {
        Disc newDisc = new Disc("newTitle", "2222222222222", "newAuthor", 0);
        MediaServiceResult result = mediaService.updateDisc(newDisc, "2222222222222");
        assertEquals(MediaServiceResult.DISC_NOT_FOUND, result);
    }

    @Test
    public void testUpdateDiscWithValidDisc() {
        Disc discOld = new Disc("title", "1234567890123", "author", 0);
        Disc newDisc = new Disc("newTitle", "1234567890123", "newAuthor", 0);
        mediaService.addDisc(discOld);
        MediaServiceResult result = mediaService.updateDisc(newDisc, "1234567890123");
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void testUpdateDiscWithEmptyDisc() {
        Disc discOld = new Disc("title", "1234567890123", "author", 0);
        Disc newDisc = new Disc(null, null, null, 0);
        mediaService.addDisc(discOld);
        MediaServiceResult result = mediaService.updateDisc(newDisc, "1234567890123");
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void testGetBooksWhichDoesNotExist(){
        MediaService mediaService = new MediaServiceImpl();
        Collection<? extends Medium> result = mediaService.getBooks();
        assertEquals(new HashMap<String, Medium>().values().isEmpty(), result.isEmpty());
    }

    @Test
    public void testGetDiscsWhichDoesNotExist(){
        MediaService mediaService = new MediaServiceImpl();
        Collection<? extends Medium> result = mediaService.getDiscs();
        assertEquals(new HashMap<String, Medium>().values().isEmpty(), result.isEmpty());
    }
}
