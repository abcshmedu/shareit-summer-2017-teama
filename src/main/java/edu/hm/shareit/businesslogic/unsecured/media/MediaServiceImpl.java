package edu.hm.shareit.businesslogic.unsecured.media;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.persistence.Persistence;
import org.apache.commons.validator.routines.ISBNValidator;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collection;
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

        if (book.getAuthor().isEmpty() || book.getIsbn().isEmpty() || book.getTitle().isEmpty()) {
            return MediaServiceResult.PARAMETER_MISSING;
        }
        book.setIsbn(validator.validateISBN13(book.getIsbn()));
        if (!validator.isValidISBN13(book.getIsbn())) {
            return MediaServiceResult.INVALID_ISBN;
        }

        if (persistence.findRecord(Book.class, book.getIsbn())) {
            return MediaServiceResult.DUPLICATE_ISBN;
        }
        book.setIsbn(validator.validateISBN13(book.getIsbn()));

        boolean addSuccessful = persistence.addRecord(book);

        if (addSuccessful) {
            return MediaServiceResult.ACCEPTED;
        } else {
            return MediaServiceResult.FAILURE;
        }
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

        if (persistence.findRecord(Disc.class, disc.getBarcode())) {
            return MediaServiceResult.DUPLICATE_DISC;
        }


        boolean addSuccessful = persistence.addRecord(disc);

        if (addSuccessful) {
            return MediaServiceResult.SUCCESS;
        } else {
            return MediaServiceResult.FAILURE;
        }
    }


    /**
     * Helper method to check for validity of barcode.
     *
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
        Collection data = persistence.getTable(Book.class);
        MediaServiceResult result = MediaServiceResult.SUCCESS;
        result.setMedia(data);
        return result;
    }

    @Override
    public MediaServiceResult getDiscs() {
        java.util.Collection data = persistence.getTable(Disc.class);
        MediaServiceResult result = MediaServiceResult.ACCEPTED;
        result.setMedia(data);
        return result;
    }

    @Override
    public MediaServiceResult updateBook(Book book, String isbn) {
        book.setIsbn(validator.validateISBN13(book.getIsbn()));
        isbn = validator.validateISBN13(isbn);

        if (book.getIsbn() != null && !book.getIsbn().equals(isbn)) {
            return MediaServiceResult.ISBN_DOES_NOT_MATCH;
        }

        Book oldBook = (Book) persistence.getRecord(Book.class, isbn);

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

        boolean updatedSuccessful = persistence.updateRecord(oldBook);

        if (updatedSuccessful) {
            return MediaServiceResult.SUCCESS;
        } else {
            return MediaServiceResult.FAILURE;
        }
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc, String barcode) {
        if (disc.getBarcode() != null && !disc.getBarcode().equals(barcode)) {
            return MediaServiceResult.DISC_DOES_NOT_MATCH;
        }

        Disc oldDisc = (Disc) persistence.getRecord(Disc.class, barcode);

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

        boolean updateSuccessful = persistence.updateRecord(oldDisc);

        if (updateSuccessful) {
            return MediaServiceResult.ACCEPTED;
        } else {
            return MediaServiceResult.FAILURE;
        }
    }

    @Override
    public MediaServiceResult getBook(String isbn) {
        isbn = validator.validateISBN13(isbn);
        Book book = (Book) persistence.getRecord(Book.class, isbn);
        MediaServiceResult result = MediaServiceResult.ACCEPTED;
        result.setMedia(Collections.singletonList(book));
        return result;
    }

    @Override
    public MediaServiceResult getDisc(String barcode) {
        Disc disc = (Disc) persistence.getRecord(Disc.class, barcode);
        MediaServiceResult result = MediaServiceResult.ACCEPTED;
        result.setMedia(Collections.singletonList(disc));
        return result;
    }
}
