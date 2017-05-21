package edu.hm.shareit.resources.secured.media;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.resources.ServiceResult;
import edu.hm.shareit.resources.secured.Authorization;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;
import edu.hm.shareit.resources.unsecured.media.MediaService;

import java.util.Collection;
import java.util.Collections;

/**
 * Implements the interface SecuredMediaService and provides functionality and logic for managing the media in the database.
 */
public class SecuredMediaServiceImpl extends Authorization implements SecuredMediaService {
    private MediaService mediaService = ServiceGetter.getMediaService();

    private static final int AUTHENTICATED_CODE = AuthenticationServiceResult.AUTHENTICATED.getCode();

    @Override
    public ServiceResult addBook(Book book, Token token) {
        AuthenticationServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return mediaService.addBook(book);
        }else{
            return authorizationRes;
        }
    }


    @Override
    public ServiceResult addDisc(Disc disc, Token token) {
        AuthenticationServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return mediaService.addDisc(disc);
        }else{
            return authorizationRes;
        }
    }

    @Override
    public ServiceResult getBooks(Token token) {
        AuthenticationServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return mediaService.getBooks();
        }else{
            return authorizationRes;
        }
    }

    @Override
    public ServiceResult getDiscs(Token token) {
        AuthenticationServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return mediaService.getDiscs();
        }else{
            return authorizationRes;
        }
    }

    @Override
    public ServiceResult updateBook(Book book, String isbn, Token token) {
        AuthenticationServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return mediaService.updateBook(book, isbn);
        }else{
            return authorizationRes;
        }
    }

    @Override
    public ServiceResult updateDisc(Disc disc, String barcode, Token token) {
        AuthenticationServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return mediaService.updateDisc(disc, barcode);
        }else{
            return authorizationRes;
        }
    }

    @Override
    public ServiceResult getBook(String isbn, Token token) {
        AuthenticationServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return mediaService.getBook(isbn);
        }else{
            return null;
        }
    }

    @Override
    public ServiceResult getDisc(String barcode, Token token) {
        AuthenticationServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return mediaService.getDisc(barcode);
        }else{
            return null;
        }
    }

    private AuthenticationServiceResult authenticate(Token token){
        return authorize(token);
    }
}
