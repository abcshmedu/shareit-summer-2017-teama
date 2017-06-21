package edu.hm.shareit.businesslogic.secured.media;

import edu.hm.shareit.businesslogic.unsecured.media.MediaServiceResult;
import edu.hm.shareit.models.Vars;
import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

/**
 * Created by Nelson on 17.06.2017.
 */
public class SecuredMediaServiceMock implements SecuredMediaService {
    private static final Book testBook = Vars.testBook;
    private static final Disc testDisc = Vars.testDisc;

    public MediaServiceResult addBook(Book book, Token token){
        return success();
    }

    public MediaServiceResult addDisc(Disc disc, Token token){
        return success();
    }

    public MediaServiceResult getBooks(Token token){
        MediaServiceResult res = MediaServiceResult.ACCEPTED;
        res.setMedia(Collections.singletonList(testBook));
        return res;
    }
    public MediaServiceResult getDiscs(Token token){
        MediaServiceResult res = MediaServiceResult.ACCEPTED;
        res.setMedia(Collections.singletonList(testDisc));
        return res;
    }

    public MediaServiceResult updateBook(Book book, String isbn, Token token){
        if(isbn == null){
            return MediaServiceResult.ISBN_NOT_FOUND;
        }else if(!isbn.equals(testBook.getIsbn())){
            return MediaServiceResult.ISBN_DOES_NOT_MATCH;
        }else {
            return MediaServiceResult.ACCEPTED;
        }
    }

    public MediaServiceResult updateDisc(Disc disc, String barcode, Token token){
        if(barcode == null){
            return MediaServiceResult.ISBN_NOT_FOUND;
        }else if(!barcode.equals(testDisc.getBarcode())){
            return MediaServiceResult.ISBN_DOES_NOT_MATCH;
        }else {
            return MediaServiceResult.ACCEPTED;
        }
    }

    public MediaServiceResult getBook(String isbn, Token token){
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
    public MediaServiceResult getDisc(String barcode, Token token){
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
        return MediaServiceResult.SUCCESS;
    }
}
