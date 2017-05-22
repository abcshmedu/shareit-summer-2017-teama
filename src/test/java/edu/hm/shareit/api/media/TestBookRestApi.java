package edu.hm.shareit.api.media;

import edu.hm.shareit.api.unsecured.media.BookRestApi;
import edu.hm.shareit.resources.ServiceGetter;

import edu.hm.shareit.models.Vars;
import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;
import edu.hm.shareit.resources.media.MockMediaServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Response;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TestBookRestApi extends BookRestApi {


    private static BookRestApi bookRestApi;
    private static Response testResponse;

    @BeforeClass
    public static void setup() {
        ServiceGetter.setMediaService(new MockMediaServiceImpl());
        bookRestApi = new BookRestApi();
    }

    @Test
    public void testGetBook() {
        //testResponse = bookRestApi.getBook(null);
        //assertEquals(null, testResponse.getEntity());

        //testResponse = bookRestApi.getBook(Vars.otherIsbn);
        //assertEquals(new Book(), testResponse.getEntity());

        //testResponse = bookRestApi.getBook(Vars.isbn);
        //assertEquals(Vars.testBook, testResponse.getEntity());
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
