package edu.hm.shareit.resources.copy;

import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.resources.media.MediaService;
import edu.hm.shareit.resources.media.MediaServiceImpl;

/**
 * Created by Nelson on 03.05.2017.
 */
public class CopyServiceImpl implements CopyService {
    MediaService mediaService = new MediaServiceImpl();
    @Override
    public CopyServiceResult addBookCopy(Copy copy, String isbn) {
        if(copy.getOwner().isEmpty()) {
            return CopyServiceResult.NO_OWNER_FOUND;
        }
        if(mediaService.getBook(isbn) == null) {
            return CopyServiceResult.ISBN_NOT_FOUND;
        }
        return CopyServiceResult.OK;
    }
}
