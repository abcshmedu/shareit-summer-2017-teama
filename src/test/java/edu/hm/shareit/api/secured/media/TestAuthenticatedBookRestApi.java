package edu.hm.shareit.api.secured.media;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.shareit.DependencyInjectionMockBindings;
import org.junit.Before;
import org.junit.Test;

public class TestAuthenticatedBookRestApi {
    private Injector injector = Guice.createInjector(new DependencyInjectionMockBindings());

    private AuthenticatedBookRestApi authenticatedBookRestApi = new AuthenticatedBookRestApi();

    private String isbn = "9783127323207";

    @Before
    public void setup() {
        injector.injectMembers(authenticatedBookRestApi);
    }

    @Test
    public void test() {
        authenticatedBookRestApi.getBook(isbn);

    }
}
