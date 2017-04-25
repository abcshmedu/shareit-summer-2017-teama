package edu.hm.shareit.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by maxl on 25.04.17.
 */
public class BookRestApiTest {

    BookRestApi bookRestApi = new BookRestApi();

    @Before
    public void setup() {
        Thread worker = new Thread(new Runnable() {
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
    }

    @Test
    public void testBooksApi() throws  JsonProcessingException, IOException{
        testGetBooksOnEmpty();
        testGetBooksAfterOnePost();
    }
    public void testGetBooksOnEmpty() throws JsonProcessingException{
        Response response = bookRestApi.getBooks();

        int status = response.getStatus();
        assertEquals(200, status);

        String reason = response.getStatusInfo().getReasonPhrase();
        assertEquals("OK", reason);

        boolean hasEntity = response.hasEntity();
        assertTrue(hasEntity);

        Object entity = response.getEntity().toString();
        assertEquals("[]", entity);
    }

    public void testGetBooksAfterOnePost() throws IOException{
        String jsonBody = "{\"title\":\"KaBlam\",\"isbn\":\"1234567890123\",\"author\":\"My Man\"}";
        Response response = bookRestApi.postBook(jsonBody);

        int status = response.getStatus();
        assertEquals(202, status);

        String reason = response.getStatusInfo().getReasonPhrase();
        assertEquals("Accepted", reason);

        response = bookRestApi.getBooks();

        status = response.getStatus();
        assertEquals(200, status);

        reason = response.getStatusInfo().getReasonPhrase();
        assertEquals("OK", reason);

        boolean hasEntity = response.hasEntity();
        assertTrue(hasEntity);

        String entity = response.getEntity().toString();
        assertEquals("[" + jsonBody + "]", entity);
    }

    public void testGetBooksAfterMultiplePosts() {
        //TODO Implement variable runner for programmatic testing
    }

    public void testGetBooksOnSpecificIsbn() {
        //TODO Implement this
    }

    public void testGetBooksOnNonexistantIsbn() {
        //TODO Implement this
    }

    //TODO Implement tests for PUT
}
