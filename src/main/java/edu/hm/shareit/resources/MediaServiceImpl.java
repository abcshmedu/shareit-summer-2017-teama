package edu.hm.shareit.resources;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//import org.json.simple.JSONObject;

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

        if(bookAuthor != null){
            oldBook.setAuthor(bookAuthor);
        }

        if(bookTitle != null){
            oldBook.setTitle(bookTitle);
        }
        return MediaServiceResult.ACCEPTED;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc, String barcode) {

        if (disc.getBarcode() != null && !disc.getBarcode().equals(barcode)) {
            return MediaServiceResult.ISBN_DOES_NOT_MATCH;
        }

        Disc oldDisc = discs.get(barcode);

        if (oldDisc == null) {
            return MediaServiceResult.ISBN_NOT_FOUND;
        }

        String discDirector = disc.getDirector();
        String discTitle = disc.getTitle();
        int discFsk = disc.getFsk();

        if(discFsk < 0){
            return MediaServiceResult.INVALID_DISC;
        } else {
            oldDisc.setFsk(discFsk);
        }

        if(discDirector != null){
            oldDisc.setDirector(discDirector);
        }

        if(discTitle != null){
            oldDisc.setTitle(discTitle);
        }

        return MediaServiceResult.ACCEPTED;
    }

    @Override
    public Book getBook(String isbn) {
        return books.get(isbn);
    }

    @Override
    public Disc getDisc(String barcode) {
        return discs.get(barcode);
    }
}
