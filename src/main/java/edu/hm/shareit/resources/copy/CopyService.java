package edu.hm.shareit.resources.copy;

import edu.hm.shareit.models.mediums.Copy;

/**
 * Created by Nelson on 03.05.2017.
 */
public interface CopyService {
    public CopyServiceResult addBookCopy(Copy copy, String isbn);
}
