package edu.hm;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import edu.hm.shareit.api.hibernate.media.BookRestApi;
import edu.hm.shareit.api.hibernate.media.DiscRestApi;
import edu.hm.shareit.resources.hibernate.media.HibernateMediaService;
import edu.hm.shareit.resources.hibernate.media.HibernateMediaServiceImpl;

public class ShareitServletContextListener extends GuiceServletContextListener {
    private static final Injector injector = Guice.createInjector(new ServletModule() {
        @Override
        protected void configureServlets() {
            bind(BookRestApi.class);
            bind(DiscRestApi.class);
            bind(HibernateMediaService.class).to(HibernateMediaServiceImpl.class);
        }
    });

    @Override
    protected Injector getInjector() {
        return injector;
    }

    /**
     * This method is only required for the HK2-Guice-Bridge in the
     * Application class.
     *
     * @return Injector instance.
     */
    static Injector getInjectorInstance() {
        return injector;

    }
}
