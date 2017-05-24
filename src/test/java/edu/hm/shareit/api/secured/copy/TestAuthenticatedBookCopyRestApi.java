package edu.hm.shareit.api.secured.copy;

import edu.hm.JettyStarter;
import edu.hm.shareit.models.authentication.User;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Copy;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.Socket;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Nelson on 22.05.2017.
 */
public class TestAuthenticatedBookCopyRestApi {
    private static final String SECURED_COPIES_URL_TO_TEST = "http://localhost:8082/shareit/copies/secured/";
    private static final String AUTHENTICATION_URL_TO_TEST = "http://localhost:8082/shareit/authentication/";

    private static JettyStarter jettyStarter = new JettyStarter();
    private static String testToken;

    @BeforeClass
    public static void setup() throws InterruptedException {
        try{
            new Socket("localhost",8082);
        } catch (IOException ex){
            new Thread(() -> {
                try {
                    jettyStarter.main();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            sleep(4_000);
        }

        Response response = ClientBuilder.newClient() // erzeuge neuen Client
                .target(AUTHENTICATION_URL_TO_TEST + "users") // setze Ressource-URL
                .request(MediaType.APPLICATION_JSON) // erzeuge Request mit JSON als Datenformat
                .post(Entity.json(new User("admin", "admin"))); // führe GET Request mit Unmarshalling des Payloads aus

        testToken = response.getStringHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
    }

    @Test
    public void testBorrowBook() {
        Response response = ClientBuilder.newClient()
                .target(SECURED_COPIES_URL_TO_TEST + "books/01234567890123")
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, testToken)
                .get();

        assertEquals(202, response.getStatus());
    }


    @Test
    public void testListBookCopies() {
        Response response = ClientBuilder.newClient()
                .target(SECURED_COPIES_URL_TO_TEST + "books")
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, testToken)
                .get();

        assertEquals(200, response.getStatus());
    }

/*
    @Test
    public void testAddBookCopy() {
        Book book = new Book();
        book.setTitle("HansWurst im Wurstland");
        Copy testCopy = new Copy(book, "Hans");

        //  create mock
        //AuthenticatedBookCopyRestApi test = mock(AuthenticatedBookCopyRestApi.class);
        // define return value for method getUniqueId()
        //when(test.addBookCopy(testCopy, "1234567890123")).thenReturn(Response.ok(200).entity(MediaType.APPLICATION_JSON).build());

        Response response = ClientBuilder.newClient()
                .target(SECURED_COPIES_URL_TO_TEST + "books/1234567890123")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(new Copy(new Book("hans", "1234567890123", "wurst"), "hans")));

        System.out.println(response.getStatus());

        assertEquals(200, response.getStatus());
    }
*/

   @Test
   public void testReturnBook() {
       Response response = ClientBuilder.newClient()
               .target(SECURED_COPIES_URL_TO_TEST + "books/9876543210987")
               .request(MediaType.APPLICATION_JSON)
               .header(HttpHeaders.AUTHORIZATION, testToken)
               .put(Entity.json(new Book("servus", "0192837465012", "hallo")));

       assertEquals(202, response.getStatus());
   }


}
