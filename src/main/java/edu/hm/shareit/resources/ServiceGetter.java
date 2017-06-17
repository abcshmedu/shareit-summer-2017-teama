package edu.hm.shareit.resources;

import edu.hm.shareit.resources.secured.authentication.AuthenticationService;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceImpl;
import edu.hm.shareit.resources.secured.media.SecuredMediaService;
import edu.hm.shareit.resources.secured.media.SecuredMediaServiceImpl;
import edu.hm.shareit.resources.unsecured.media.MediaService;
import edu.hm.shareit.resources.unsecured.media.MediaServiceImpl;

import java.util.logging.Logger;

/**
 * Utility Class for ensuring static usage of the Service Classes.
 * <p>
 * Also provides setters for testing or altering default behavior
 * MUST call setters before instantiating other classes utilizing Services
 */
public final class ServiceGetter {

    private static Logger logger = Logger.getLogger(ServiceGetter.class.getName());

    //Default HibernateMediaService is HibernateMediaServiceImpl
    private static MediaService mediaService = new MediaServiceImpl();

    //Default SecuredMediaService is SecuredMediaServiceImpl
    private static SecuredMediaService securedMediaService = new SecuredMediaServiceImpl();

    //Default AuthenticationService is AuthenticationServiceImpl
    private static AuthenticationService authenticationService = new AuthenticationServiceImpl();

    /**
     * Statically returns an instance of a HibernateMediaService.
     *
     * @return An instance of a SecuredMediaService
     */
    public static MediaService getMediaService() {
        return mediaService;
    }

    /**
     * Statically sets the HibernateMediaService to the one provided.
     *
     * @param mediaService The SecuredMediaService to use
     */
    public static void setMediaService(MediaService mediaService) {
        ServiceGetter.mediaService = mediaService;
    }

    /**
     * Statically returns an instance of a SecuredMediaService.
     *
     * @return An instance of a SecuredMediaService
     */
    public static SecuredMediaService getSecuredMediaService() {
        return securedMediaService;
    }

    /**
     * Statically sets the SecuredMediaService to the one provided.
     *
     * @param securedMediaService The SecuredMediaService to use
     */
    public static void setSecuredMediaService(SecuredMediaService securedMediaService) {
        ServiceGetter.securedMediaService = securedMediaService;
    }

    /**
     * Statically returns an instance of an AuthenticationService.
     *
     * @return An instance of a AuthenticationService
     */
    public static AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    /**
     * Statically sets the AuthenticationService to the one provided.
     *
     * @param authenticationService The CopyService to use
     */
    public static void setAuthenticationService(AuthenticationService authenticationService) {
        ServiceGetter.authenticationService = authenticationService;
    }
}
