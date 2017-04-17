package edu.hm.shareit.resources;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MediaServiceImpl implements MediaService {
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Disc> discs = new HashMap<>();

    @Override
    public MediaServiceResult addBook(Book book) {
        if (book.getAuthor().isEmpty() ||
                book.getIsbn().isEmpty() || book.getTitle().isEmpty()) {
            return MediaServiceResult.PARAMETER_MISSING;
        }

        if (!isValidISBN(book.getIsbn())) {
            return MediaServiceResult.INVALID_ISBN;
        }

        if (books.containsKey(book.getIsbn())) {
            return MediaServiceResult.DUPLICATE_ISBN;
        }

        books.put(book.getIsbn(), book);
        return MediaServiceResult.ACCEPTED;
    }


    @Override
    public MediaServiceResult addDisc(Disc disc) {
        if (disc.getBarcode().isEmpty() |
                disc.getDirector().isEmpty() |
                //disc.getFsk() < 0 |
                disc.getTitle().isEmpty()) {
            return MediaServiceResult.PARAMETER_MISSING;
        }

        if (!isValidBarcode(disc.getBarcode())) {
            return MediaServiceResult.INVALID_DISC;
        }

        if (discs.containsKey(disc.getBarcode())) {
            return MediaServiceResult.DUPLICATE_DISC;
        }

        discs.put(disc.getBarcode(), disc);
        return MediaServiceResult.ACCEPTED;
    }

    private boolean isValidISBN(String isbn) {
        boolean result = true;
        // ToDo Check if isbn of book is valid
        return result;
    }


    private boolean isValidBarcode(String barcode) {
        boolean result = true;
        //ToDo check for valid barcode
        return result;
    }

    @Override
    public Collection<? extends Medium> getBooks() {
        return books.values();
    }

    @Override
    public Collection<? extends Medium> getDiscs() {
        return discs.values();
    }

    @Override
    public MediaServiceResult updateBook(Book book) {
        return null;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        return null;
    }
}
