package edu.hm.shareit.api.unsecured.media;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import edu.hm.shareit.DependencyInjectionMockBindings;
import edu.hm.shareit.models.Vars;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.resources.unsecured.media.MediaService;
import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TestBookRestApi extends BookRestApi {

    private static Response testResponse;

    @Inject
    private Injector injector = Guice.createInjector(DependencyInjectionMockBindings.getSingletonInjection());

    private BookRestApi bookRestApi = new BookRestApi();

    @Before
    public void setup() {
        injector.injectMembers(bookRestApi);
    }

    @Test
    public void testGetBook() {
        testResponse = bookRestApi.getBook(null);
        assertEquals(null, testResponse.getEntity());

        testResponse = bookRestApi.getBook(Vars.otherIsbn);
        assertEquals(new Book(), testResponse.getEntity());

        testResponse = bookRestApi.getBook(Vars.isbn);
        assertEquals(Vars.testBook, testResponse.getEntity());
    }

    @Test
    public void testGetBooks() {
        testResponse = bookRestApi.getBooks();
        assertEquals(Collections.singletonList(Vars.testBook), testResponse.getEntity());
    }

    @Test
    public void testPostBook() {
        testResponse = bookRestApi.postBook(Vars.testBook);
        assertEquals(MediaServiceResult.ACCEPTED, testResponse.getEntity());
    }

    @Test
    public void testUpdateBook() {
        testResponse = bookRestApi.updateBook(Vars.testBook, null);
        assertEquals(MediaServiceResult.ISBN_NOT_FOUND, testResponse.getEntity());

        testResponse = bookRestApi.updateBook(Vars.testBook, Vars.otherIsbn);
        assertEquals(MediaServiceResult.ISBN_DOES_NOT_MATCH, testResponse.getEntity());

        testResponse = bookRestApi.updateBook(Vars.testBook, Vars.isbn);
        assertEquals(MediaServiceResult.ACCEPTED, testResponse.getEntity());
    }
}
