package edu.hm.shareit.resources;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;

import java.util.Collection;

public interface MediaService {

    MediaServiceResult addBook(Book book);
    MediaServiceResult addDisc(Disc disc);
    Collection<? extends Medium> getBooks();
    Collection<? extends Medium> getDiscs();
    MediaServiceResult updateBook(Book book);
    MediaServiceResult updateDisc(Disc disc);
    Book getBook(String isbn);
    Disc getDisc(String barcode);
}
