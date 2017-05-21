package edu.hm.shareit.resources.unsecured.media;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;

import java.util.*;

/**
 * Implements the interface SecuredMediaService and provides functionality and logic for managing the media in the database.
 */
public class MediaServiceImpl implements MediaService {
    private final int isbnBarcodeLength = 13;
    private final int isbnBarcodeValidStart = 48;
    private final int isbnBarcodeValidEnd = 57;
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Disc> discs = new HashMap<>();

    @Override
    public MediaServiceResult addBook(Book book) {
        if (book == null || book.getIsbn() == null || book.getTitle() == null || book.getAuthor() == null) {
            return MediaServiceResult.PARAMETER_MISSING;
        }

        if (book.getAuthor().isEmpty()
                || book.getIsbn().isEmpty() || book.getTitle().isEmpty()) {
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
        if (disc == null || disc.getBarcode() == null || disc.getDirector() == null || disc.getTitle() == null) {
            return MediaServiceResult.PARAMETER_MISSING;
        }

        if (disc.getBarcode().isEmpty()
                | disc.getDirector().isEmpty()
                | disc.getTitle().isEmpty()) {
            return MediaServiceResult.PARAMETER_MISSING;
        }

        if (!isValidBarcode(disc.getBarcode())) {
            return MediaServiceResult.INVALID_BARCODE;
        }

        if (discs.containsKey(disc.getBarcode())) {
            return MediaServiceResult.DUPLICATE_DISC;
        }

        discs.put(disc.getBarcode(), disc);
        return MediaServiceResult.ACCEPTED;
    }

    /**
     * Helper method to check for validity of isbn.
     * @param isbn the isbn to be checked.
     * @return Isbn valid or not.
     */
    private boolean isValidISBN(String isbn) {
        if (isbn.length() != isbnBarcodeLength) {
            return false;
        }

        final char[] isbnChars = isbn.toCharArray();
        for (char i : isbnChars) {
            if (i < isbnBarcodeValidStart || i > isbnBarcodeValidEnd) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method to check for validity of barcode.
     * @param barcode The barcode to be checked.
     * @return Barcode is valid or not.
     */
    private boolean isValidBarcode(String barcode) {
        if (barcode.length() != isbnBarcodeLength) {
            return false;
        }
        final char[] barcodeChars = barcode.toCharArray();
        for (char i : barcodeChars) {
            if (i < isbnBarcodeValidStart || i > isbnBarcodeValidEnd) {
                return false;
            }
        }
        return true;
    }

    @Override
    public MediaServiceResult getBooks() {
        MediaServiceResult res = MediaServiceResult.ACCEPTED;
        Collection theBooks = books.values();
        res.setMedia(theBooks);
        return res;
    }

    @Override
    public MediaServiceResult getDiscs() {
        MediaServiceResult res = MediaServiceResult.ACCEPTED;
        Collection theDiscs = discs.values();
        res.setMedia(theDiscs);
        return res;
    }

    @Override
    public MediaServiceResult updateBook(Book book, String isbn) {
        if (book.getIsbn() != null && !book.getIsbn().equals(isbn)) {
            return MediaServiceResult.ISBN_DOES_NOT_MATCH;
        }

        Book oldBook = books.get(isbn);

        if (oldBook == null) {
            return MediaServiceResult.ISBN_NOT_FOUND;
        }

        String bookAuthor = book.getAuthor();
        String bookTitle = book.getTitle();

        if (bookAuthor != null) {
            oldBook.setAuthor(bookAuthor);
        }

        if (bookTitle != null) {
            oldBook.setTitle(bookTitle);
        }
        return MediaServiceResult.ACCEPTED;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc, String barcode) {

        if (disc.getBarcode() != null && !disc.getBarcode().equals(barcode)) {
            return MediaServiceResult.DISC_DOES_NOT_MATCH;
        }

        Disc oldDisc = discs.get(barcode);

        if (oldDisc == null) {
            return MediaServiceResult.DISC_NOT_FOUND;
        }

        String discDirector = disc.getDirector();
        String discTitle = disc.getTitle();
        int discFsk = disc.getFsk();

        if (discFsk < 0) {
            return MediaServiceResult.INVALID_DISC;
        } else {
            oldDisc.setFsk(discFsk);
        }

        if (discDirector != null) {
            oldDisc.setDirector(discDirector);
        }

        if (discTitle != null) {
            oldDisc.setTitle(discTitle);
        }

        return MediaServiceResult.ACCEPTED;
    }

    @Override
    public MediaServiceResult getBook(String isbn) {
        MediaServiceResult res = MediaServiceResult.ACCEPTED;
        Book book = books.get(isbn);
        res.setMedia(Collections.singletonList(book));
        return res;
    }

    @Override
    public MediaServiceResult getDisc(String barcode) {
        MediaServiceResult res = MediaServiceResult.ACCEPTED;
        Disc disc = discs.get(barcode);
        res.setMedia(Collections.singletonList(disc));
        return res;
    }
}
