package edu.hm.shareit.restapi.secured.media;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.shareit.DependencyInjectionMockBindings;
import edu.hm.shareit.businesslogic.unsecured.media.MediaServiceResult;
import edu.hm.shareit.models.mediums.Disc;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nelson on 22.05.2017.
 */
public class TestAuthenticatedDiscRestApi {
    Disc disc1 = new Disc("disc1", "123456789012", "director1", 16);

    private Injector injector = Guice.createInjector(DependencyInjectionMockBindings.getSingletonInjection());

    private AuthenticatedDiscRestApi authenticatedDiscRestApi = new AuthenticatedDiscRestApi();

    @Before
    public void setup() {
        injector.injectMembers(authenticatedDiscRestApi);
    }

    @Test
    public void testGetDisc() {
        Response response = authenticatedDiscRestApi.getDisc("1234567890123");
        assertEquals(response.getStatus(), MediaServiceResult.SUCCESS.getCode());
    }

    @Test
    public void testGetDiscs() {
        Response response = authenticatedDiscRestApi.getDiscs();
        assertEquals(response.getStatus(), MediaServiceResult.SUCCESS.getCode());
    }

    @Test
    public void testPostDisc() {
        Response response = authenticatedDiscRestApi.postDisc(disc1);
        assertEquals(response.getStatus(), MediaServiceResult.SUCCESS.getCode());
    }

    @Test
    public void testUpdateDisc() {
        Response response = authenticatedDiscRestApi.updateDisc(disc1, "1234567890123");
        assertEquals(response.getStatus(), MediaServiceResult.ACCEPTED.getCode());
    }
}
