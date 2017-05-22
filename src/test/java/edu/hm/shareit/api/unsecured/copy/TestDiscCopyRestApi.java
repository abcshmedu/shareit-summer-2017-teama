package edu.hm.shareit.api.unsecured.copy;

import edu.hm.JettyStarter;
import edu.hm.shareit.models.mediums.Copy;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class TestDiscCopyRestApi {
    private static final String DISCS_URL_TO_TEST = "http://localhost:8082/shareit/copies/discs";
    private static JettyStarter jettyStarter;

    @BeforeClass
    public void setup() throws Exception {
        jettyStarter = new JettyStarter();
        jettyStarter.main();
    }

    @Test
    public void testAddDiscCopy() {
        Response response = ClientBuilder.newClient() // erzeuge neuen Client
                .target(DISCS_URL_TO_TEST + "/1234567890123") // setze Ressource-URL
                .request(MediaType.APPLICATION_JSON) // erzeuge Request mit HTML als Datenformat
                .post(Entity.json(new Copy(null, "test"))); // führe GET Request mit Unmarshalling des Payloads aus

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testBorrowDiscCopy() {
        Response response = ClientBuilder.newClient() // erzeuge neuen Client
                .target(DISCS_URL_TO_TEST + "/1234567890123") // setze Ressource-URL
                .request(MediaType.APPLICATION_JSON) // erzeuge Request mit HTML als Datenformat
                .get(); // führe GET Request mit Unmarshalling des Payloads aus

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testListDiscOfCopies() {
        Response response = ClientBuilder.newClient() // erzeuge neuen Client
                .target(DISCS_URL_TO_TEST) // setze Ressource-URL
                .request(MediaType.APPLICATION_JSON) // erzeuge Request mit HTML als Datenformat
                .get(); // führe GET Request mit Unmarshalling des Payloads aus

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testReturnCopyOfDisc() {
        Response response = ClientBuilder.newClient() // erzeuge neuen Client
                .target(DISCS_URL_TO_TEST + "/1234567890123") // setze Ressource-URL
                .request(MediaType.APPLICATION_JSON) // erzeuge Request mit HTML als Datenformat
                .put(Entity.json(new Copy(null, "test"))); // führe GET Request mit Unmarshalling des Payloads aus

        assertEquals(200, response.getStatus());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        synchronized (JettyStarter.MONITOR) {
            jettyStarter.notifyAll();
        }
    }

}
