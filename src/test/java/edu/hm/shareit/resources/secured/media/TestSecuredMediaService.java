package edu.hm.shareit.resources.secured.media;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.shareit.DependencyInjectionMockBindings;
import edu.hm.shareit.models.Vars;
import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.resources.ServiceResult;
import edu.hm.shareit.resources.secured.MockAuthorization;
import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;
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
}
