package edu.hm.shareit.api.unsecured.copy;

import edu.hm.shareit.models.mediums.Copy;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class TestBookCopyRestApi {
    private static final String BOOKS_URL_TO_TEST = "http://localhost:8082/shareit/copies/books";

    @Test
    public void testAddBookCopy() {
        Response response = ClientBuilder.newClient() // erzeuge neuen Client
                .target(BOOKS_URL_TO_TEST + "/1234567890123") // setze Ressource-URL
                .request(MediaType.APPLICATION_JSON) // erzeuge Request mit HTML als Datenformat
                .post(Entity.json(new Copy(null, "test"))); // führe GET Request mit Unmarshalling des Payloads aus

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testBorrowBookCopy() {
        Response response = ClientBuilder.newClient() // erzeuge neuen Client
                .target(BOOKS_URL_TO_TEST + "/1234567890123") // setze Ressource-URL
                .request(MediaType.APPLICATION_JSON) // erzeuge Request mit HTML als Datenformat
                .get(); // führe GET Request mit Unmarshalling des Payloads aus

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testListBookOfCopies() {
        Response response = ClientBuilder.newClient() // erzeuge neuen Client
                .target(BOOKS_URL_TO_TEST) // setze Ressource-URL
                .request(MediaType.APPLICATION_JSON) // erzeuge Request mit HTML als Datenformat
                .get(); // führe GET Request mit Unmarshalling des Payloads aus

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testReturnCopyOfBook() {
        Response response = ClientBuilder.newClient() // erzeuge neuen Client
                .target(BOOKS_URL_TO_TEST + "/1234567890123") // setze Ressource-URL
                .request(MediaType.APPLICATION_JSON) // erzeuge Request mit HTML als Datenformat
                .put(Entity.json(new Copy(null, "test"))); // führe GET Request mit Unmarshalling des Payloads aus

        assertEquals(202, response.getStatus());
    }
}
