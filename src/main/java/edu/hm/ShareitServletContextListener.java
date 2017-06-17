package edu.hm;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import edu.hm.shareit.persistence.Persistence;
import edu.hm.shareit.persistence.PersistenceImpl;
import edu.hm.shareit.resources.secured.Authorization;
import edu.hm.shareit.resources.secured.AuthorizationImpl;
import edu.hm.shareit.resources.secured.authentication.AuthenticationService;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceImpl;
import edu.hm.shareit.resources.unsecured.media.MediaService;
import edu.hm.shareit.resources.unsecured.media.MediaServiceImpl;

public class ShareitServletContextListener extends GuiceServletContextListener {
    private static final Injector injector = Guice.createInjector(new ServletModule() {
        @Override
        protected void configureServlets() {
            //bind(BookRestApi.class);
            //bind(DiscRestApi.class);
            bind(Persistence.class).to(PersistenceImpl.class);
            bind(MediaService.class).to(MediaServiceImpl.class);
            bind(AuthenticationService.class).to(AuthenticationServiceImpl.class);
            bind(Authorization.class).to(AuthorizationImpl.class);
        }
    });

    /**
     * This method is only required for the HK2-Guice-Bridge in the
     * Application class.
     *
     * @return Injector instance.
     */
    static Injector getInjectorInstance() {
        return injector;

    }

    @Override
    protected Injector getInjector() {
        return injector;
    }
}
