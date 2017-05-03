package edu.hm.shareit.resources;

import edu.hm.shareit.models.mediums.Copy;

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
