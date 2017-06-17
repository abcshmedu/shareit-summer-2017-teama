package edu.hm.toDelete.media;

import edu.hm.shareit.models.Vars;
import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.resources.unsecured.media.MediaService;
import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

/**
 * Mock Implementation of the SecuredMediaService for Testing
 *
 * If a method is called it will simply return preset values.
 */
public class MockSecuredMediaServiceImpl implements MediaService {
    private static final Book testBook = Vars.testBook;
    private static final Disc testDisc = Vars.testDisc;

    public MediaServiceResult addBook(Book book){
        return success();
    }

    public MediaServiceResult addDisc(Disc disc){
        return success();
    }

    public MediaServiceResult getBooks(){
        MediaServiceResult res = MediaServiceResult.ACCEPTED;
        res.setMedia(Collections.singletonList(testBook));
        return res;
    }
    public MediaServiceResult getDiscs(){
        MediaServiceResult res = MediaServiceResult.ACCEPTED;
        res.setMedia(Collections.singletonList(testDisc));
        return res;
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

    public MediaServiceResult getBook(String isbn){
        MediaServiceResult res = MediaServiceResult.ACCEPTED;
        if(isbn == null){
            res.setMedia(null);
        }else if(!isbn.equals(testBook.getIsbn())){
            res.setMedia(Collections.singletonList(new Book()));
        }else {
            res.setMedia(Collections.singletonList(testBook));
        }
        return res;
    }
    public MediaServiceResult getDisc(String barcode){
        MediaServiceResult res = MediaServiceResult.ACCEPTED;
        if(barcode == null){
            res.setMedia(null);
        }else if(!barcode.equals(testDisc.getBarcode())){
            res.setMedia(Collections.singletonList(new Disc()));
        }else {
            res.setMedia(Collections.singletonList(testDisc));
        }
        return res;
    }

    public Response authorize(Token token) {
        return Response
                .status(Response.Status.ACCEPTED)
                .entity("Valid token")
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

    private MediaServiceResult success(){
        return MediaServiceResult.ACCEPTED;
    }
}
