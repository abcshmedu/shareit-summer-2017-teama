package edu.hm.shareit.resources;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;
//import org.json.simple.JSONObject;

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
        if (isbn.length() != 13) {
            return false;
        }

        final char[] isbnChars = isbn.toCharArray();
        for (char i : isbnChars) {
            if (i < 48 || i > 57) {
                return false;
            }
        }
        return true;
        //ToDo See if better way of checking ISBN, potentially using regex?
    }


    private boolean isValidBarcode(String barcode) {
        if (barcode.length() != 12) {
            return false;
        }
        final char[] barcodeChars = barcode.toCharArray();
        for (char i : barcodeChars) {
            if (i < 48 || i > 57) {
                return false;
            }
        }
        return true;
        //ToDo See if there is a better way of doing this
    }

    @Override
    public Collection<? extends Medium> getBooks() {
        return books.values();
    }

    @Override
    public Collection<? extends Disc> getDiscs() {
        return discs.values();
    }

    @Override
    public MediaServiceResult updateBook(Book book) {
        boolean result = false;
        for (Medium m : getBooks()) {
            Book bookIt = (Book) m;
            if (bookIt.getIsbn().equals(book.getIsbn())) {
                result = true;
                break;
            }
        }
        if (!result) {
            return MediaServiceResult.INVALID_ISBN;
        }
        books.put(book.getIsbn(), book);
        return MediaServiceResult.ACCEPTED;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        boolean result = false;
        for (Medium m : getDiscs()) {
            Disc discIt = (Disc) m;
            if (discIt.getBarcode().equals(disc.getBarcode())) {
                result = true;
                break;
            }
        }
        if (!result) {
            return MediaServiceResult.INVALID_BARCODE;
        }
        discs.put(disc.getBarcode(), disc);
        return MediaServiceResult.ACCEPTED;
    }

    @Override
    public Book getBook(String isbn) {
        for (Medium b : getBooks()) {
            Book book = (Book) b;
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
        //ToDo think about what to do with null value if isbn is non-existent
    }

    @Override
    public Disc getDisc(String barcode) {
        for (Medium b : getDiscs()) {
            Disc disc = (Disc) b;
            if (disc.getBarcode().equals(barcode)) {
                return disc;
            }
        }
        return null;
        //ToDo think about what to do with null value if isbn is non-existent

    }
}
