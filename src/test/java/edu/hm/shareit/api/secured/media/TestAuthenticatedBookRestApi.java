package edu.hm.shareit.api.secured.media;

import edu.hm.JettyStarter;
import edu.hm.shareit.models.authentication.User;
import org.junit.Before;
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
public class TestAuthenticatedBookRestApi {
    private static final String SECURED_MEDIA_URL_TO_TEST = "http://localhost:8082/shareit/media/secured/";
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
    public void testGetBook() {
        Response response = ClientBuilder.newClient()
                .target(SECURED_MEDIA_URL_TO_TEST + "books/01234567890123")
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, testToken)
                .get();

        assertEquals(202, response.getStatus());
    }
}
