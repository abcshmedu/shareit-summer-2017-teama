package edu.hm.shareit.businesslogic.secured.media;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.shareit.DependencyInjectionMockBindings;
import edu.hm.shareit.businesslogic.ServiceResult;
import edu.hm.shareit.businesslogic.secured.MockAuthorization;
import edu.hm.shareit.businesslogic.secured.authentication.AuthenticationServiceResult;
import edu.hm.shareit.businesslogic.unsecured.media.MediaServiceResult;
import edu.hm.shareit.models.Vars;
import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Book;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSecuredMediaService extends MockAuthorization {

    private final Book bookWithValidISBNWithoutDashes = new Book("Title1", "9783127323207", "Author1");
    private final Book bookWithValidISBNWithDashes = new Book("Title2", "978-3-12-732320-7", "Author2");
    private final Book bookWithWrongISBN = new Book("Title3", "1234567890123", "Author3");
    private final Token validToken = new Token("valid");
    private final Token nonValidToken = new Token("nonValidToken");
    private Injector injector = Guice.createInjector(DependencyInjectionMockBindings.getSingletonInjection());
    private SecuredMediaService securedMediaService = new SecuredMediaServiceImpl();

    @Before
    public void setup() {
        injector.injectMembers(securedMediaService);
    }

    @Test
    public void addBookWithValidToken() {
        ServiceResult result = securedMediaService.addBook(Vars.testBook, validToken);
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void addBookWithInvalidToken() {
        ServiceResult result = securedMediaService.addBook(Vars.testBook, nonValidToken);
        assertEquals(AuthenticationServiceResult.TOKEN_NOT_VALID, result);
    }

    @Test
    public void addDiscWithValidToken() {
        ServiceResult result = securedMediaService.addDisc(Vars.testDisc, validToken);
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void addDiscWithInvalidToken() {
        ServiceResult result = securedMediaService.addDisc(Vars.testDisc, nonValidToken);
        assertEquals(AuthenticationServiceResult.TOKEN_NOT_VALID, result);
    }

    @Test
    public void getBooksWithValidToken() {
        ServiceResult result = securedMediaService.getBooks(validToken);
        assertEquals(MediaServiceResult.ACCEPTED, result);

    }

    @Test
    public void getBooksWithInvalidToken() {
        ServiceResult result = securedMediaService.getBooks(nonValidToken);
        assertEquals(AuthenticationServiceResult.TOKEN_NOT_VALID, result);
    }

    @Test
    public void getDiscsWithValidToken() {
        ServiceResult result = securedMediaService.getDiscs(validToken);
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void getDiscsWithInvalidToken() {
        ServiceResult result = securedMediaService.getDiscs(nonValidToken);
        assertEquals(AuthenticationServiceResult.TOKEN_NOT_VALID, result);
    }


    @Test
    public void updateBookWithValidToken() {
        ServiceResult result = securedMediaService.updateBook(Vars.testBook, "1234567890123", validToken);
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void updateBookWithInvalidToken() {
        ServiceResult result = securedMediaService.updateBook(Vars.testBook, "1234567890123", nonValidToken);
        assertEquals(AuthenticationServiceResult.TOKEN_NOT_VALID, result);
    }

    @Test
    public void updateDiscWithValidToken() {
        ServiceResult result = securedMediaService.updateDisc(Vars.testDisc, Vars.testDisc.getBarcode(), validToken);
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void updateDiscWithInvalidToken() {
        ServiceResult result = securedMediaService.updateDisc(Vars.testDisc, "1234567890123", nonValidToken);
        assertEquals(AuthenticationServiceResult.TOKEN_NOT_VALID, result);
    }

    @Test
    public void getBookWithValidToken() {
        ServiceResult result = securedMediaService.getBook(Vars.testBook.getIsbn(), validToken);
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void getBookWithInvalidToken() {
        ServiceResult result = securedMediaService.getBook("1234567890123", nonValidToken);
        assertEquals(null, result);
    }

    @Test
    public void getDiscWithValidToken() {
        ServiceResult result = securedMediaService.getDisc("1234567890123", validToken);
        assertEquals(MediaServiceResult.ACCEPTED, result);
    }

    @Test
    public void getDiscWithInvalidToken() {
        ServiceResult result = securedMediaService.getDisc("1234567890123", nonValidToken);
        assertEquals(null, result);
    }


}
