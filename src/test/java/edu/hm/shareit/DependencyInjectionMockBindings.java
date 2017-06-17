package edu.hm.shareit;

import com.google.inject.AbstractModule;
import edu.hm.shareit.resources.secured.authentication.AuthenticationService;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceMock;

public class DependencyInjectionMockBindings extends AbstractModule {
    @Override
    protected void configure() {

     /*
      * This tells Guice that whenever it sees a dependency on a IDatabase,
      * it should satisfy the dependency using a Database.
      */
        bind(AuthenticationService.class).to(AuthenticationServiceMock.class);
    }
}