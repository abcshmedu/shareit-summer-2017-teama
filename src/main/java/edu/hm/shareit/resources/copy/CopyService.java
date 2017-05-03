package edu.hm.shareit.resources.copy;

import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.models.mediums.Medium;

import java.util.Collection;

/**
 * Created by Nelson on 03.05.2017.
 */
public interface CopyService {
    CopyServiceResult addBookCopy(Copy copy, String isbn);

    Collection<Copy> listBookCopies();

    CopyServiceResult borrowBook(String isbn);

    CopyServiceResult returnBook(String isbn);

    CopyServiceResult addDiscCopy(Copy copy, String barcode);

    Collection<Copy> listDiscCopies();

    CopyServiceResult borrowDisc(String barcode);

    CopyServiceResult returnDisc(String barcode);
}
