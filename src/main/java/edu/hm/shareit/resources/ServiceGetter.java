package edu.hm.shareit.resources;

import edu.hm.shareit.resources.copy.CopyService;
import edu.hm.shareit.resources.copy.CopyServiceImpl;
import edu.hm.shareit.resources.media.MediaService;
import edu.hm.shareit.resources.media.MediaServiceImpl;

/**
 * Utility Class for ensuring static usage of the Service Classes
 *
 * Also provides setters for testing or altering default behavior
 * MUST call setters before instantiating other classes utilizing Services
 */
public class ServiceGetter {

    //Default MediaService is MediaServiceImpl
    private static MediaService MEDIA_SERVICE = new MediaServiceImpl();

    //Default CopyService is CopyServiceImpl
    private static CopyService COPY_SERVICE = new CopyServiceImpl();

    /**
     * Statically returns an instance of a MediaService
     * @return An instance of a MediaService
     */
    public static MediaService getMediaService(){
        return MEDIA_SERVICE;
    }

    /**
     * Statically returns an instance of a CopyService
     * @return An instance of a CopyService
     */
    public static CopyService getCopyService(){
        return COPY_SERVICE;
    }

    /**
     * Statically sets the MediaService to the one provided
     * @param mediaService The MediaService to use
     */
    public static void setMediaService(MediaService mediaService) {
        MEDIA_SERVICE = mediaService;
    }

    /**
     * Statically sets the CopyService to the one provided
     * @param copyService The CopyService to use
     */
    public static void setCopyService(CopyService copyService) {
        COPY_SERVICE = copyService;
    }
}
