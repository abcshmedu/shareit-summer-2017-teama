package edu.hm.shareit.api.exeptionhandlers;

import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nelson on 17.06.2017.
 */
public class TestExceptionHandlers {

    @BeforeClass
    public static void setup() throws Exception {
        final String APP_URL = "/";
        final int PORT = 8082;
        final String WEBAPP_DIR = "./src/main/webapp/";

        final Logger logger = LogManager.getLogger();
        Server jetty = new Server(PORT);
        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        jetty.start();
        logger.info("Jetty listening on port " + PORT);
    }

    @Test
    public void testUnrecognizedPropertyException() {
        String invalidBook = "{\n" +
                        "\"title:\" " + "\"title,\"\n" +
                "\"author\": " + "\"author\",\n" +
                "\"isbn\": " + "\"978-3-16-148410-0\"\n" +
                "}";


        Response response = ClientBuilder.newClient()
                .target("http://localhost:8082/shareit/media")
                .path("books")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(invalidBook));

        assertEquals(response.getStatus(), 400);
    }
}
