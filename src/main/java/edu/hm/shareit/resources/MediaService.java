package edu.hm.shareit.resources;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;

public interface MediaService {

    MediaServiceResult addBook(Book book);
    MediaServiceResult addBook(Disc disc);
    Medium[] getBooks();
    Medium[] getDiscs();
    MediaServiceResult updateBook(Book book);
    MediaServiceResult updateDisc(Disc disc);
}
