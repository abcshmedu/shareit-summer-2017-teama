package edu.hm.shareit.resources.media;

import edu.hm.shareit.models.mediums.Vars;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;
import edu.hm.shareit.resources.media.MediaService;
import edu.hm.shareit.resources.media.MediaServiceResult;

import java.util.Collection;
import java.util.Collections;

/**
 * Mock Implementation of the MediaService for Testing
 *
 * If a method is called it will simply return preset values.
 */
public class MockMediaServiceImpl implements MediaService {
    private static final Book testBook = Vars.testBook;
    private static final Disc testDisc = Vars.testDisc;

    public MediaServiceResult addBook(Book book){
        return success();
    }

    public MediaServiceResult addDisc(Disc disc){
        return success();
    }

    public Collection<? extends Medium> getBooks(){
        return Collections.singletonList(testBook);
    }
    public Collection<? extends Medium> getDiscs(){
        return Collections.singletonList(testDisc);
    }
    public MediaServiceResult updateBook(Book book, String isbn){
        if(isbn == null){
            return MediaServiceResult.ISBN_NOT_FOUND;
        }else if(!isbn.equals(testBook.getIsbn())){
            return MediaServiceResult.ISBN_DOES_NOT_MATCH;
        }else {
            return MediaServiceResult.ACCEPTED;
        }
    }
    public MediaServiceResult updateDisc(Disc disc, String barcode){
        if(barcode == null){
            return MediaServiceResult.ISBN_NOT_FOUND;
        }else if(!barcode.equals(testDisc.getBarcode())){
            return MediaServiceResult.ISBN_DOES_NOT_MATCH;
        }else {
            return MediaServiceResult.ACCEPTED;
        }
    }
    public Book getBook(String isbn){
        if(isbn == null){
            return null;
        }else if(!isbn.equals(testBook.getIsbn())){
            return new Book();
        }else {
            return testBook;
        }
    }
    public Disc getDisc(String barcode){
        if(barcode == null){
            return null;
        }else if(!barcode.equals(testDisc.getBarcode())){
            return new Disc();
        }else {
            return testDisc;
        }
    }

    private MediaServiceResult success(){
        return MediaServiceResult.ACCEPTED;
    }
}
