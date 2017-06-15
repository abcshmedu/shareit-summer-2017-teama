package edu.hm.shareit.api.secured.copy;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.resources.ServiceResult;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;
import edu.hm.shareit.resources.secured.copy.SecuredCopyService;
import edu.hm.shareit.resources.unsecured.copy.CopyServiceResult;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
* Rest API for book copies.
* Receives requests for adding/borrowing/returning copies of books to/from the database.
 */
@Path("books")
public class AuthenticatedBookCopyRestApi {

    @Inject
    private SecuredCopyService copyService;

    //Used for authorization
    @HeaderParam(HttpHeaders.AUTHORIZATION)
    private String tokenStr = null;
    /**
     * Method called for borrowing books from the database.
     * Checks whether there are any copies left of the book specified in the isbn parameter and responds accordingly.
     * @param isbn The book of which a copy is requested.
     * @return The response containing a status code and message.
     */
    @GET
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrowBook(@PathParam("isbn") String isbn) {
        Token token = new Token(tokenStr);
        ServiceResult result = copyService.borrowBook(isbn, token);
        return Response.ok(result.getStatus()).status(result.getCode()).build();
    }

    /**
     * Shows a list of all book copies that exist in the database.
     * @return List of book copies
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listBookCopies() {
        Token token = new Token(tokenStr);
        ServiceResult result = copyService.listBookCopies(token);
        if(result instanceof AuthenticationServiceResult){
            return Response.ok(result.getStatus()).status(result.getCode()).build();
        }else{
            CopyServiceResult copyRes = (CopyServiceResult) result;
            Collection collection = copyRes.getCopies();
            return Response.ok(collection).build();
        }
    }

    /**
     * Adds a book copy to the database if the provided isbn exists.
     * @param copyWithOwner An instance of Copy which provides the owner's name.
     * @param isbn The isbn number of the book of which a copy is to be added.
     * @return The response containing a status code and message.
     */
    @POST
    @Path("{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBookCopy(Copy copyWithOwner, @PathParam("isbn") String isbn) {
        Token token = new Token(tokenStr);
        ServiceResult result = copyService.addBookCopy(copyWithOwner, isbn, token);
        return Response.ok(result.getStatus()).status(result.getCode()).build();
    }

    /**
     * Receives a request for returning a book to the database.
     * @param isbn The isbn number of the book of which a copy is to be returned.
     * @return The response containing a status code and message.
     */
    @PUT
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnBook(@PathParam("isbn") String isbn) {
        Token token = new Token(tokenStr);
        ServiceResult result = copyService.returnBook(isbn, token);
        return Response.ok(result).status(result.getCode()).build();
    }
}
