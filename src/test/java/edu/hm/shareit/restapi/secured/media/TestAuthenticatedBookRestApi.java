package edu.hm.shareit.restapi.secured.media;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.shareit.DependencyInjectionMockBindings;
import edu.hm.shareit.businesslogic.unsecured.media.MediaServiceResult;
import edu.hm.shareit.models.mediums.Book;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class TestAuthenticatedBookRestApi {

    Book book1 = new Book();
    private String isbn = "9783127323207";

    private Injector injector = Guice.createInjector(DependencyInjectionMockBindings.getSingletonInjection());

    private AuthenticatedBookRestApi authenticatedBookRestApi = new AuthenticatedBookRestApi();

    @Before
    public void setup() {
        injector.injectMembers(authenticatedBookRestApi);
    }

    @Test
    public void testGetBook() {
        Response response = authenticatedBookRestApi.getBook(isbn);
        assertEquals(response.getStatus(), MediaServiceResult.SUCCESS.getCode());
    }

    @Test
    public void testGetBooks() {
        Response response = authenticatedBookRestApi.getBooks();
        assertEquals(response.getStatus(), MediaServiceResult.SUCCESS.getCode());
    }

    @Test
    public void testPostBook() {
        Response response = authenticatedBookRestApi.postBook(book1);
        assertEquals(response.getStatus(), MediaServiceResult.SUCCESS.getCode());
    }

    @Test
    public void testUpdateBook() {
        authenticatedBookRestApi.updateBook(book1, isbn);

    }
}
