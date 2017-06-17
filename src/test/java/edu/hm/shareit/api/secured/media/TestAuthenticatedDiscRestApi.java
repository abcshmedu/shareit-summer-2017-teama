package edu.hm.shareit.api.secured.media;

import edu.hm.JettyStarter;
import edu.hm.shareit.models.authentication.User;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.toDelete.media.MockSecuredMediaServiceImpl;
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

/**
 * Created by Nelson on 22.05.2017.
 */
public class TestAuthenticatedDiscRestApi {
    private static final String SECURED_MEDIA_URL_TO_TEST = "http://localhost:8082/shareit/media/secured/";
    private static final String AUTHENTICATION_URL_TO_TEST = "http://localhost:8082/shareit/authentication/";

    private static JettyStarter jettyStarter = new JettyStarter();
    private static String testToken;
    private static Disc disc1;
    private static AuthenticatedDiscRestApi discRestApi;
    private static Response testResponse;
    //private AuthenticatedBookRestApi mockAuthenticatedBookRestApi;

    @BeforeClass
    public static void setup() throws InterruptedException {

        ServiceGetter.setMediaService(new MockSecuredMediaServiceImpl());
        discRestApi = new AuthenticatedDiscRestApi();

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
        disc1 = new Disc();
        // Stubbing methods
        //when(mockAuthenticatedBookRestApi.postBook(book1)).thenReturn(Response.ok().status(200).build());
    }

    @Test
    public void testGetDisc() {
        Response response = ClientBuilder.newClient()
                .target(SECURED_MEDIA_URL_TO_TEST + "discs/01234567890123")
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, testToken)
                .get();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetDiscs() {
        Response response = ClientBuilder.newClient()
                .target(SECURED_MEDIA_URL_TO_TEST + "discs")
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
