package edu.hm.shareit.resources.unsecured;


import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.shareit.DependencyInjectionMockBindings;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.resources.unsecured.media.MediaServiceImpl;
import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMediaServiceImpl {

    private Injector injector = Guice.createInjector(DependencyInjectionMockBindings.getSingletonInjection());

    private MediaServiceImpl mediaService = new MediaServiceImpl();

    private Book bookWithMissingParam = new Book(null, "9783127323207", "Author");
    private Book bookWithEmptyAuthor = new Book("title", "9783127323207", "");
    private Book bookWithInvalidISBN = new Book("title", "0000000000000", "Author");
    private Book validBook = new Book("valid", "9783127323207", "Author");
    private Book validBookWithOtherISBN = new Book("valid", "978-3-940006-12-7", "Author");
    private Book invalidBook = new Book("invalid", "978-3-940006-12-7", "Author");

    private Disc discWithMissingParam = new Disc(null, "9783127323207", "Director", 18);
    private Disc discWithEmptyAuthor = new Disc("title", "9783127323207", "", 18);
    private Disc discWithInvalidBarcode = new Disc("title", "1234", "Director", 18);
    private Disc validDisc = new Disc("valid", "9783127323207", "Director", 18);
    private Disc validDiscWithOtherBarcode = new Disc("valid", "9783940006127", "Director", 18);
    private Disc invalidDisc = new Disc("invalid", "9783940006127", "Director", 18);

    // addBook-Tests
    @Before
    public void setup() {
        injector.injectMembers(mediaService);
    }

    @Test
    public void addBookWithNullParameter() {
        MediaServiceResult result = mediaService.addBook(bookWithMissingParam);
        assertEquals(MediaServiceResult.PARAMETER_MISSING, result);
    }

    @Test
    public void addBookWithEmptyParameter() {
        MediaServiceResult result = mediaService.addBook(bookWithEmptyAuthor);
        assertEquals(MediaServiceResult.PARAMETER_MISSING, result);
    }

    @Test
    public void addBookWithInvalidISBN() {
        MediaServiceResult result = mediaService.addBook(bookWithInvalidISBN);
        assertEquals(MediaServiceResult.INVALID_ISBN, result);
    }

    @Test
    public void addBookDuplicateISBN() {
        MediaServiceResult result = mediaService.addBook(validBook);
        assertEquals(MediaServiceResult.DUPLICATE_ISBN, result);
    }

    @Test
    public void addBookvalidISBN() {
        MediaServiceResult result = mediaService.addBook(validBookWithOtherISBN);
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void addBookinvalidISBN() {
        MediaServiceResult result = mediaService.addBook(invalidBook);
        assertEquals(MediaServiceResult.FAILURE, result);
    }

    // addDisc-Tests
    @Test
    public void addDiscWithNullParameter() {
        MediaServiceResult result = mediaService.addDisc(discWithMissingParam);
        assertEquals(MediaServiceResult.PARAMETER_MISSING, result);
    }

    @Test
    public void addDiscWithEmptyParameter() {
        MediaServiceResult result = mediaService.addDisc(discWithEmptyAuthor);
        assertEquals(MediaServiceResult.PARAMETER_MISSING, result);
    }

    @Test
    public void addDiscWithInvalidBarcode() {
        MediaServiceResult result = mediaService.addDisc(discWithInvalidBarcode);
        assertEquals(MediaServiceResult.INVALID_BARCODE, result);
    }

    @Test
    public void addDiscDuplicateISBN() {
        MediaServiceResult result = mediaService.addDisc(validDisc);
        assertEquals(MediaServiceResult.DUPLICATE_DISC, result);
    }

    @Test
    public void addDiscvalidBarcode() {
        MediaServiceResult result = mediaService.addDisc(validDiscWithOtherBarcode);
        assertEquals(MediaServiceResult.SUCCESS, result);
    }

    @Test
    public void addDiscinvalidISBN() {
        MediaServiceResult result = mediaService.addDisc(invalidDisc);
        assertEquals(MediaServiceResult.FAILURE, result);
    }

    @Test
    public void notAValidBarcode() {
        MediaServiceResult result = mediaService.addDisc(discWithInvalidBarcode);
        assertEquals(MediaServiceResult.INVALID_BARCODE, result);
    }

    // Get all books and discs
    @Test
    public void getBooksTest() {
        MediaServiceResult result = mediaService.getBooks();
        assertEquals(MediaServiceResult.SUCCESS, result);
    }

    @Test
    public void getDiscsTest() {
        MediaServiceResult result = mediaService.getDiscs();
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    //Update Book and Disc
    @Test
    public void updateBookWithISBNMismatch() {
        MediaServiceResult result = mediaService.updateBook(validBook, "000");
        assertEquals(MediaServiceResult.ISBN_DOES_NOT_MATCH, result);
    }

    @Test
    public void updateNoneExistingBook() {
        MediaServiceResult result = mediaService.updateBook(new Book("title", "9783548289014", "Author"), "9783548289014");
        assertEquals(MediaServiceResult.ISBN_NOT_FOUND, result);
    }

    @Test
    public void updateExistingBook() {
        MediaServiceResult result = mediaService.updateBook(new Book("title", "9783127323207", "AuthorNew"), "9783127323207");
        assertEquals(MediaServiceResult.SUCCESS, result);
    }

    @Test
    public void updateExistingBookWithFailure() {
        MediaServiceResult result = mediaService.updateBook(new Book("nonTitle", "9783127323207", "AuthorNew"), "9783127323207");
        assertEquals(MediaServiceResult.FAILURE, result);
    }


    @Test
    public void updateDiscWithBarcodeMismatch() {
        MediaServiceResult result = mediaService.updateDisc(validDisc, "000");
        assertEquals(MediaServiceResult.DISC_DOES_NOT_MATCH, result);
    }

    @Test
    public void updateNoneExistingDisc() {
        MediaServiceResult result = mediaService.updateDisc(new Disc("title", "9783548289014", "Director", 18), "9783548289014");
        assertEquals(MediaServiceResult.DISC_NOT_FOUND, result);
    }

    //get specific book and disc tests

    @Test
    public void getBookTest() {
        MediaServiceResult result = mediaService.getBook("9783548289014");
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void getDiscTest() {
        MediaServiceResult result = mediaService.getDisc("9783548289014");
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

}
