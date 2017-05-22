package edu.hm.shareit.resources;

import edu.hm.shareit.resources.secured.authentication.AuthenticationService;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceImpl;
import edu.hm.shareit.resources.secured.copy.SecuredCopyService;
import edu.hm.shareit.resources.secured.copy.SecuredCopyServiceImpl;
import edu.hm.shareit.resources.secured.media.SecuredMediaService;
import edu.hm.shareit.resources.secured.media.SecuredMediaServiceImpl;
import edu.hm.shareit.resources.unsecured.copy.CopyService;
import edu.hm.shareit.resources.unsecured.copy.CopyServiceImpl;
import edu.hm.shareit.resources.unsecured.media.MediaService;
import edu.hm.shareit.resources.unsecured.media.MediaServiceImpl;

/**
 * Utility Class for ensuring static usage of the Service Classes.
 *
 * Also provides setters for testing or altering default behavior
 * MUST call setters before instantiating other classes utilizing Services
 */
public final class ServiceGetter {
    //Default MediaService is MediaServiceImpl
    private static MediaService mediaService = new MediaServiceImpl();

    //Default SecuredMediaService is SecuredMediaServiceImpl
    private static SecuredMediaService securedMediaService = new SecuredMediaServiceImpl();

    //Default CopyService is SecuredCopyServiceImpl
    private static CopyService copyService = new CopyServiceImpl();

    //Default SecuredCopyService is SecuredCopyServiceImpl
    private static SecuredCopyService securedCopyService = new SecuredCopyServiceImpl();

    //Default AuthenticationService is AuthenticationServiceImpl
    private static AuthenticationService authenticationService = new AuthenticationServiceImpl();

    /**
     * Statically returns an instance of a MediaService.
     *
     * @return An instance of a SecuredMediaService
     */
    public static MediaService getMediaService() {
        return mediaService;
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
     * Statically returns an instance of a CopyService.
     *
     * @return An instance of a CopyService
     */
    public static CopyService getCopyService() {
        return copyService;
    }

    /**
     * Statically returns an instance of a SecuredCopyService.
     *
     * @return An instance of a SecuredCopyService
     */
    public static SecuredCopyService getSecuredCopyService() {
        return securedCopyService;
    }
    /**
     * Statically returns an instance of an AuthenticationService.
     *
     * @return An instance of a CopyService
     */
    public static AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    /**
     * Statically sets the MediaService to the one provided.
     *
     * @param mediaService The SecuredMediaService to use
     */
    public static void setMediaService(MediaService mediaService) {
        ServiceGetter.mediaService = mediaService;
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
     * Statically sets the CopyService to the one provided.
     *
     * @param copyService The CopyService to use
     */
    public static void setCopyService(CopyService copyService) {
        ServiceGetter.copyService = copyService;
    }

    /**
     * Statically sets the SecuredCopyService to the one provided.
     *
     * @param securedCopyService The SecuredCopyService to use
     */
    public static void setSecuredCopyService(SecuredCopyService securedCopyService) {
        ServiceGetter.securedCopyService = securedCopyService;
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
