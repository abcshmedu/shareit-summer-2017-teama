package edu.hm.shareit.api.unsecured.media;

import edu.hm.JettyStarter;
import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.Vars;
import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;
import edu.hm.shareit.resources.unsecured.media.MockMediaServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Response;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TestDiscRestApi extends DiscRestApi {


    private static DiscRestApi discRestApi;
    private static Response testResponse;

    JettyStarter jettyStarter;

    @BeforeClass
    public static void setup() {
        ServiceGetter.setMediaService(new MockMediaServiceImpl());
        discRestApi = new DiscRestApi();
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


}

