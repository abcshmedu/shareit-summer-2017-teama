package edu.hm.shareit.api.secured.authentication;

import edu.hm.JettyStarter;
import edu.hm.shareit.models.authentication.User;
import edu.hm.shareit.models.mediums.Copy;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class TestAuthenticationRestApi {

    private static final String AUTHENTICATION_URL_TO_TEST = "http://localhost:8082/shareit/authentication/";

    private static JettyStarter jettyStarter = new JettyStarter();

    @BeforeClass
    public static void setup() throws Exception {
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
    }

    @Test
    public void testUserLoginAndAuthorization() {
        Response response = ClientBuilder.newClient() // erzeuge neuen Client
                .target(AUTHENTICATION_URL_TO_TEST + "users") // setze Ressource-URL
                .request(MediaType.APPLICATION_JSON) // erzeuge Request mit JSON als Datenformat
                .post(Entity.json(new User("admin", "admin"))); // führe GET Request mit Unmarshalling des Payloads aus

        assertEquals(201, response.getStatus());

        final String token = response.getStringHeaders().get("Authorization").get(0);
        response = ClientBuilder.newClient()
                .target(AUTHENTICATION_URL_TO_TEST + "users")
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .get();

        assertEquals(200, response.getStatus());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        System.out.println("after class");
        synchronized (JettyStarter.MONITOR) {
            JettyStarter.MONITOR.notifyAll();
        }
    }
}
