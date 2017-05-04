package edu.hm.shareit.resources.copy;

import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.resources.media.MediaService;

import java.util.ArrayList;
import java.util.Collection;

public class CopyServiceImpl implements CopyService {
    private MediaService MEDIA_SERVICE = ServiceGetter.getMediaService();

    @Override
    public CopyServiceResult addBookCopy(Copy copy, String isbn) {
        if (copy.getOwner().isEmpty()) {
            return CopyServiceResult.NO_OWNER_FOUND;
        }
        System.out.println(MEDIA_SERVICE.getBooks());
        if (MEDIA_SERVICE.getBook(isbn) == null) {
            return CopyServiceResult.ISBN_NOT_FOUND;
        }
        return CopyServiceResult.OK;
    }

    @Override
    public Collection<Copy> listBookCopies() {
        ArrayList<Copy> list = new ArrayList<>();
        list.add(new Copy(new Book("title", "1234567890123", "author"), "owner"));
        list.add(new Copy(new Book("title1", "3214567890123", "author1"), "owner1"));

        return list;
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
    public Collection<Copy> listDiscCopies() {
        ArrayList<Copy> list = new ArrayList<>();
        list.add(new Copy(new Disc("title", "barcode", "director", 18), "owner"));
        list.add(new Copy(new Disc("title1", "barcode1", "director1", 18), "owner1"));
        return list;
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
