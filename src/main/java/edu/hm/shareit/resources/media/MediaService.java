package edu.hm.shareit.resources.media;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;

import java.util.Collection;

public interface MediaService {

    MediaServiceResult addBook(Book book);
    MediaServiceResult addDisc(Disc disc);
    Collection<? extends Medium> getBooks();
    Collection<? extends Medium> getDiscs();
    MediaServiceResult updateBook(Book book, String isbn);
    MediaServiceResult updateDisc(Disc disc, String isbn);
    Book getBook(String isbn);
    Disc getDisc(String barcode);
}
