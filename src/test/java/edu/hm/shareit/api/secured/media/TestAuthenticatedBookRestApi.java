package edu.hm.shareit.api.secured.media;

import edu.hm.JettyStarter;
import edu.hm.shareit.api.unsecured.media.BookRestApi;
import edu.hm.shareit.models.Vars;
import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.authentication.User;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.resources.media.MockMediaServiceImpl;
import edu.hm.shareit.resources.media.MockSecuredMediaServiceImpl;
import edu.hm.shareit.resources.secured.media.SecuredMediaService;
import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.AuthenticationException;
import javax.print.attribute.standard.Media;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Nelson on 22.05.2017.
 */
public class TestAuthenticatedBookRestApi extends AuthenticatedBookRestApi {
    private static final String SECURED_MEDIA_URL_TO_TEST = "http://localhost:8082/shareit/media/secured/";
    private static final String AUTHENTICATION_URL_TO_TEST = "http://localhost:8082/shareit/authentication/";

    private static JettyStarter jettyStarter = new JettyStarter();
    private static String testToken;
    private static Book book1;
    private static AuthenticatedBookRestApi bookRestApi;
    private static Response testResponse;
    //private AuthenticatedBookRestApi mockAuthenticatedBookRestApi;

    @BeforeClass
    public static void setup() throws InterruptedException {

        ServiceGetter.setMediaService(new MockSecuredMediaServiceImpl());
        bookRestApi = new AuthenticatedBookRestApi();

        try {
            new Socket("localhost", 8082);
        } catch (IOException ex) {
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

        // Mocking setup //
        //Create mock object of SecuredMediaService
        //mockAuthenticatedBookRestApi = mock(AuthenticatedBookRestApi.class);

        // Test instances
        book1 = new Book("title", "01234567890123", "testAuthor");
        // Stubbing methods
        //when(mockAuthenticatedBookRestApi.postBook(book1)).thenReturn(Response.ok().status(200).build());
    }

    @Test
    public void testGetBook() {
        Response response = ClientBuilder.newClient()
                .target(SECURED_MEDIA_URL_TO_TEST + "books/01234567890123")
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, testToken)
                .get();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetBooks() {
        Response response = ClientBuilder.newClient()
                .target(SECURED_MEDIA_URL_TO_TEST + "books")
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, testToken)
                .get();

        assertEquals(200, response.getStatus());
    }

    //@Test
    //public void testPostBook() {
    //    Response response = ClientBuilder.newClient()
    //            .target(SECURED_MEDIA_URL_TO_TEST + "books")
    //            .request(MediaType.APPLICATION_JSON)
    //            .header(HttpHeaders.AUTHORIZATION, testToken)
    //            .post(Entity.json(book1));
//
    //    assertEquals(200, response.getStatus());
    //}

    //@Test
    //public void testUpdateBook() {
    //    Response response = ClientBuilder.newClient()
    //            .target(SECURED_MEDIA_URL_TO_TEST + "books/0123456789012")
    //            .request(MediaType.APPLICATION_JSON)
    //            .header(HttpHeaders.AUTHORIZATION, testToken)
    //            .put(Entity.json(book1));
//
    //    assertEquals(200, response.getStatus());
    //}
}
