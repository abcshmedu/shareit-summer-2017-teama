package edu.hm.shareit.api;

import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Vars;
import edu.hm.shareit.resources.media.MediaServiceResult;
import edu.hm.shareit.resources.media.MockMediaServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Response;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TestDiscRestApi extends DiscRestApi{

    private static Thread worker;

    private static DiscRestApi discRestApi;
    private static Response testResponse;

    @BeforeClass
    public static void setup() {
        worker = new Thread(new Runnable() {
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
        discRestApi = new DiscRestApi();
        discRestApi.setMediaService(new MockMediaServiceImpl());
    }

    @Test
    public void testGetDisc() {
        testResponse = discRestApi.getDisc(null);
        assertEquals(null, testResponse.getEntity());

        testResponse = discRestApi.getDisc(Vars.otherBarcode);
        assertEquals(new Disc(), testResponse.getEntity());

        testResponse = discRestApi.getDisc(Vars.barcode);
        assertEquals(Vars.testDisc, testResponse.getEntity());
    }

    @Test
    public void testGetDiscs() {
        testResponse = discRestApi.getDiscs();
        assertEquals(Collections.singletonList(Vars.testDisc), testResponse.getEntity());
    }

    @Test
    public void testPostBook() {
        testResponse = discRestApi.postDisc(Vars.testDisc);
        assertEquals(MediaServiceResult.ACCEPTED, testResponse.getEntity());
    }

    @Test
    public void testUpdateBook() {
        testResponse = discRestApi.updateDisc(Vars.testDisc, null);
        assertEquals(MediaServiceResult.ISBN_NOT_FOUND, testResponse.getEntity());

        testResponse = discRestApi.updateDisc(Vars.testDisc, Vars.otherBarcode);
        assertEquals(MediaServiceResult.ISBN_DOES_NOT_MATCH, testResponse.getEntity());

        testResponse = discRestApi.updateDisc(Vars.testDisc, Vars.barcode);
        assertEquals(MediaServiceResult.ACCEPTED, testResponse.getEntity());
    }

    @AfterClass
    public static void tearDown(){
        try{
            worker.join(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

