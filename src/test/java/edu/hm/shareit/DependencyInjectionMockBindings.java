package edu.hm.shareit;

import com.google.inject.AbstractModule;
import edu.hm.shareit.resources.secured.authentication.AuthenticationService;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceMock;
import edu.hm.shareit.resources.unsecured.media.MediaService;
import edu.hm.shareit.resources.unsecured.media.MockMediaServiceImpl;

public class DependencyInjectionMockBindings extends AbstractModule {

    private static final DependencyInjectionMockBindings singletonInjection = new DependencyInjectionMockBindings();

    private DependencyInjectionMockBindings() {
    }

    public static DependencyInjectionMockBindings getSingletonInjection() {
        return singletonInjection;
    }

    @Override
    protected void configure() {

     /*
      * This tells Guice that whenever it sees a dependency on a IDatabase,
      * it should satisfy the dependency using a Database.
      */
        bind(AuthenticationService.class).to(AuthenticationServiceMock.class);
        bind(MediaService.class).to(MockMediaServiceImpl.class);
    }
}