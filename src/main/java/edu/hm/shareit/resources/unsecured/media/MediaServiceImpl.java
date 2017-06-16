package edu.hm.shareit.resources.unsecured.media;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.persistence.Persistence;
import org.apache.commons.validator.routines.ISBNValidator;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collections;


/**
 * Implements the interface SecuredMediaService and provides functionality and logic for managing the media in the database.
 */
public class MediaServiceImpl implements MediaService, Serializable {
    private final int isbnBarcodeLength = 13;
    private final int isbnBarcodeValidStart = 48;
    private final int isbnBarcodeValidEnd = 57;
    private final ISBNValidator validator = new ISBNValidator();

    @Inject
    private Persistence persistence;

    @Override
    public MediaServiceResult addBook(Book book) {
        if (book == null || book.getIsbn() == null || book.getTitle() == null || book.getAuthor() == null) {
            return MediaServiceResult.PARAMETER_MISSING;
        }

        if (book.getAuthor().isEmpty()
                || book.getIsbn().isEmpty() || book.getTitle().isEmpty()) {
            return MediaServiceResult.PARAMETER_MISSING;
        }

        if (!validator.isValidISBN13(book.getIsbn())) {
            return MediaServiceResult.INVALID_ISBN;
        }

        if (ifExists(Book.class, book.getIsbn())) {
            return MediaServiceResult.DUPLICATE_ISBN;
        }

        return persistence.addRecord(book);
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


        if (ifExists(Disc.class, disc.getBarcode())) {
            return MediaServiceResult.DUPLICATE_DISC;
        }

        return persistence.addRecord(disc);
    }

    /**
     * Helper method to check for existance of Id
     *
     * @param clazz the class of the Object to check
     * @param object the id to check
     */
    private boolean ifExists(Class clazz, Serializable object){
        MediaServiceResult result = persistence.findRecord(clazz, object);
        return result.getCode() == MediaServiceResult.SUCCESS.getCode();
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
        return persistence.getTable(Book.class);
    }

    @Override
    public MediaServiceResult getDiscs() {
        return persistence.getTable(Disc.class);
    }

    @Override
    public MediaServiceResult updateBook(Book book, String isbn) {
        if (book.getIsbn() != null && !book.getIsbn().equals(isbn)) {
            return MediaServiceResult.ISBN_DOES_NOT_MATCH;
        }

        MediaServiceResult result = persistence.findRecord(Book.class, isbn);
        Book oldBook = (Book) result.getMedia().toArray()[0];

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

        return persistence.updateRecord(oldBook);
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc, String barcode) {

        if (disc.getBarcode() != null && !disc.getBarcode().equals(barcode)) {
            return MediaServiceResult.DISC_DOES_NOT_MATCH;
        }

        MediaServiceResult result = persistence.findRecord(Disc.class, barcode);
        Disc oldDisc = (Disc) result.getMedia().toArray()[0];

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

        return persistence.updateRecord(oldDisc);
    }

    @Override
    public MediaServiceResult getBook(String isbn) {
        MediaServiceResult result = persistence.findRecord(Book.class, isbn);
        Book book = (Book) result.getMedia().toArray()[0];
        result.setMedia(Collections.singletonList(book));
        return result;
    }

    @Override
    public MediaServiceResult getDisc(String barcode) {
        MediaServiceResult result = persistence.findRecord(Disc.class, barcode);
        Disc disc = (Disc) result.getMedia().toArray()[0];
        result.setMedia(Collections.singletonList(disc));
        return result;
    }
}
