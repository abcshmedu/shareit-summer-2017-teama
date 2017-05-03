package edu.hm.shareit.resources;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;

import javax.inject.Inject;
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
