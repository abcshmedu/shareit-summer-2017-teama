package edu.hm.shareit.api.unsecured.media;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.JettyStarter;
import edu.hm.shareit.DependencyInjectionMockBindings;
import edu.hm.shareit.models.Vars;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TestDiscRestApi extends DiscRestApi {

    private static Response testResponse;

    JettyStarter jettyStarter;

    private Injector injector = Guice.createInjector(DependencyInjectionMockBindings.getSingletonInjection());

    private DiscRestApi discRestApi = new DiscRestApi();

    @Before
    public void setup() {
        injector.injectMembers(discRestApi);
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

