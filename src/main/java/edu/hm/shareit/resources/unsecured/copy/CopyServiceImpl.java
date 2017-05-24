package edu.hm.shareit.resources.unsecured.copy;

import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.resources.unsecured.media.MediaService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implements the interface CopyService and provides functionality and logic for managing the copies in the database.
 */
public class CopyServiceImpl implements CopyService {
    private final int fskConstant = 18;
    private MediaService mediaService = ServiceGetter.getMediaService();

    @Override
    public CopyServiceResult addBookCopy(Copy copy, String isbn) {
        //if (copy.getOwner().isEmpty()) {
        //    return CopyServiceResult.NO_OWNER_FOUND;
        //}
        //System.out.println(mediaService.getBooks());
        //if (mediaService.getBook(isbn) == null) {
        //    return CopyServiceResult.ISBN_NOT_FOUND;
        //}
        return CopyServiceResult.OK;
    }

    @Override
    public CopyServiceResult listBookCopies() {
        ArrayList<Copy> list = new ArrayList<>();
        list.add(new Copy(new Book("title", "1234567890123", "author"), "owner"));
        list.add(new Copy(new Book("title1", "3214567890123", "author1"), "owner1"));

        CopyServiceResult res = CopyServiceResult.OK;
        res.setCopies(list);
        return res;
    }

    @Override
    public CopyServiceResult borrowBook(String isbn) {
        return CopyServiceResult.ACCEPTED;
    }

    @Override
    public CopyServiceResult returnBook(String isbn) {
        return CopyServiceResult.ACCEPTED;
    }

    @Override
    public CopyServiceResult addDiscCopy(Copy copy, String barcode) {
        return CopyServiceResult.OK;
    }

    @Override
    public CopyServiceResult listDiscCopies() {
        ArrayList<Copy> list = new ArrayList<>();
        list.add(new Copy(new Disc("title", "barcode", "director", fskConstant), "owner"));
        list.add(new Copy(new Disc("title1", "barcode1", "director1", fskConstant), "owner1"));

        CopyServiceResult res = CopyServiceResult.OK;
        res.setCopies(list);
        return res;
    }

    @Override
    public CopyServiceResult borrowDisc(String barcode) {
        return CopyServiceResult.OK;
    }

    @Override
    public CopyServiceResult returnDisc(String barcode) {
        return CopyServiceResult.OK;
    }
}
