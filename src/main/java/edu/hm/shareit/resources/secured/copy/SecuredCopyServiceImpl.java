package edu.hm.shareit.resources.secured.copy;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.resources.ServiceResult;
import edu.hm.shareit.resources.secured.Authorization;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;
import edu.hm.shareit.resources.unsecured.copy.CopyService;

/**
 * Implements the interface CopyService and provides functionality and logic for managing the copies in the database.
 */
public class SecuredCopyServiceImpl extends Authorization implements SecuredCopyService {
    private CopyService copyService = ServiceGetter.getCopyService();

    private static final int AUTHENTICATED_CODE = AuthenticationServiceResult.AUTHENTICATED.getCode();

    @Override
    public ServiceResult addBookCopy(Copy copy, String isbn, Token token) {
        ServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return copyService.addBookCopy(copy, isbn);
        }else{
            return authorizationRes;
        }
    }

    @Override
    public ServiceResult listBookCopies(Token token) {
        ServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return copyService.listBookCopies();
        }else{
            return authorizationRes;
        }
    }

    @Override
    public ServiceResult borrowBook(String isbn, Token token) {
        ServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return copyService.borrowBook(isbn);
        }else{
            return authorizationRes;
        }
    }

    @Override
    public ServiceResult returnBook(String isbn, Token token) {
        ServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return copyService.returnBook(isbn);
        }else{
            return authorizationRes;
        }
    }

    @Override
    public ServiceResult addDiscCopy(Copy copy, String barcode, Token token) {
        ServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return copyService.addDiscCopy(copy, barcode);
        }else{
            return authorizationRes;
        }
    }

    @Override
    public ServiceResult listDiscCopies(Token token) {
        ServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return copyService.listDiscCopies();
        }else{
            return authorizationRes;
        }
    }

    @Override
    public ServiceResult borrowDisc(String barcode, Token token) {
        ServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return copyService.borrowDisc(barcode);
        }else{
            return authorizationRes;
        }
    }

    @Override
    public ServiceResult returnDisc(String barcode, Token token) {
        ServiceResult authorizationRes = authenticate(token);
        if(authorizationRes.getCode() == AUTHENTICATED_CODE){
            return copyService.returnDisc(barcode);
        }else{
            return authorizationRes;
        }
    }

    private AuthenticationServiceResult authenticate(Token token){
        return authorize(token);
    }
}
