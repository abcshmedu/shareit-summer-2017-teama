package edu.hm.shareit.api.secured.media;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.shareit.DependencyInjectionMockBindings;
import org.junit.Before;
import org.junit.Test;


public class TestAuthenticatedDiscRestApi {

    private Injector injector = Guice.createInjector(DependencyInjectionMockBindings.getSingletonInjection());

    private AuthenticatedDiscRestApi authenticatedDiscRestApi = new AuthenticatedDiscRestApi();


    @Before
    public void setup() {
        injector.injectMembers(authenticatedDiscRestApi);
    }

    @Test
    public void startWritingTestsBrosef() {
        // ToDo
    }

}
