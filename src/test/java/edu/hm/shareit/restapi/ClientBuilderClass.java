package edu.hm.shareit.restapi;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

/**
 * Created by Nelson on 04.05.2017.
 */
public class ClientBuilderClass {
    public static void main(String[] args) throws ParseException {
        String jsonToPost = "{\n" + "\"title\" : \"hans\",\n" + "\"isbn\" : \"1234567890123\",\n" +
                "\"author\" : \"Juergen Mueller\"\n" + "}";

        Response response = ClientBuilder.newClient()                       //erzeuge neuen Client
                .target("http://localhost:8082/shareit/media/books")    //setze Ressource-URL
                .request(MediaType.APPLICATION_JSON)                        //erzeuge Request mit HTML als Datenformat
                .post(Entity.json(jsonToPost));                             // führe GET Request mit Unmarshalling des Payloads aus

        System.out.println(response.toString());

        String responsePayload = ClientBuilder.newClient() //erzeuge neuen Client
                .target("http://localhost:8082/shareit/media/books") //setze Ressource-URL
                .request(MediaType.APPLICATION_JSON) //erzeuge Request mit HTML als Datenformat
                .get(String.class);        // führe GET Request mit Unmarshalling des Payloads aus
        System.out.println(responsePayload);
    }
}
