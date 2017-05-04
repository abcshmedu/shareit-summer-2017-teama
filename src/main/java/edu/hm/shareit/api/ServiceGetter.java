package edu.hm.shareit.api;

import edu.hm.shareit.resources.copy.CopyService;
import edu.hm.shareit.resources.copy.CopyServiceImpl;
import edu.hm.shareit.resources.media.MediaService;
import edu.hm.shareit.resources.media.MediaServiceImpl;

public class ServiceGetter {

    private static MediaService MEDIA_SERVICE = new MediaServiceImpl();
    private static CopyService COPY_SERVICE = new CopyServiceImpl();

    public static MediaService getMediaService(){
        return MEDIA_SERVICE;
    }

    public static CopyService getCopyService(){
        return COPY_SERVICE;
    }

    public static void setCopyService(CopyService copyService) {
        COPY_SERVICE = copyService;
    }

    public static void setMediaService(MediaService mediaService) {
        MEDIA_SERVICE = mediaService;
    }
}
