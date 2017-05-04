package edu.hm.shareit.api.media;

import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.models.mediums.Book;

import edu.hm.shareit.models.mediums.Vars;
import edu.hm.shareit.resources.media.MediaServiceResult;
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

    private static Thread worker;

    private static BookRestApi bookRestApi;
    private static Response testResponse;

    @BeforeClass
    public static void setup() {
        worker = new Thread(new Runnable() {
            @Override
            public void run() {
                String APP_URL = "/";
                int PORT = 8082;
                String WEBAPP_DIR = "./src/main/webapp/";
                Server jetty = new Server(PORT);
                jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));

                try {
                    jetty.start();
                    jetty.join();
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    jetty.destroy();
                }
            }
        });
        worker.start();
        ServiceGetter.setMediaService(new MockMediaServiceImpl());
        bookRestApi = new BookRestApi();
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

    @AfterClass
    public static void tearDown(){
        try{
            worker.join(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
