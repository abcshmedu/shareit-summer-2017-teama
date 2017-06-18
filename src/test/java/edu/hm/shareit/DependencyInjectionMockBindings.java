package edu.hm.shareit;

import com.google.inject.AbstractModule;
import edu.hm.shareit.persistence.Persistence;
import edu.hm.shareit.persistence.PersistenceMock;
import edu.hm.shareit.resources.secured.Authorization;
import edu.hm.shareit.resources.secured.MockAuthorization;
import edu.hm.shareit.resources.secured.authentication.AuthenticationService;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceMock;
import edu.hm.shareit.resources.secured.media.MediaServiceMock;
import edu.hm.shareit.resources.secured.media.SecuredMediaService;
import edu.hm.shareit.resources.secured.media.SecuredMediaServiceMock;
import edu.hm.shareit.resources.unsecured.media.MediaService;

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
        bind(MediaService.class).to(MediaServiceMock.class);
        bind(Authorization.class).to(MockAuthorization.class);
        bind(SecuredMediaService.class).to(SecuredMediaServiceMock.class);
        bind(Persistence.class).to(PersistenceMock.class);
    }
}