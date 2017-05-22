package edu.hm.shareit.api.secured.copy;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.resources.ServiceResult;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;
import edu.hm.shareit.resources.secured.copy.SecuredCopyService;
import edu.hm.shareit.resources.unsecured.copy.CopyServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
* Rest API for disc copies.
* Receives requests for adding copies of discs to the database.
 */
@Path("discs")
public class AuthenticatedDiscCopyRestApi {
    private SecuredCopyService securedCopyService = ServiceGetter.getSecuredCopyService();

    //Used for authentication
    @HeaderParam(HttpHeaders.AUTHORIZATION)
    private String tokenStr;

    /**
     * Shows a list of all disc copies that exist in the database.
     * @return List of disc copies
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listDiscCopies() {
        Token token = new Token(tokenStr);
        ServiceResult result = securedCopyService.listBookCopies(token);
        if(result instanceof AuthenticationServiceResult){
            return Response.ok(result.getStatus()).status(result.getCode()).build();
        }else{
            CopyServiceResult copyRes = (CopyServiceResult) result;
            Collection collection = copyRes.getCopies();
            return Response.ok(collection).build();
        }
    }
    /**
     * Method called for borrowing discs from the database.
     * Checks whether there are any copies left of the disc specified in the barcode parameter and responds accordingly.
     * @param barcode The disc of which a copy is requested.
     * @return The response containing a status code and message.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{barcode}")
    public Response borrowDisc(@PathParam("barcode") String barcode) {
        Token token = new Token(tokenStr);
        ServiceResult result = securedCopyService.borrowDisc(barcode, token);
        return Response.ok(result.getStatus()).status(result.getCode()).build();
    }
    /**
     * Adds a disc copy to the database if the provided barcode exists.
     * @param copyWithOwner An instance of Copy which provides the owner's name.
     * @param barcode The barcode number of the disc of which a copy is to be added.
     * @return The response containing a status code and message.
     */
    @POST
    @Path("{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDiscCopy(Copy copyWithOwner, @PathParam("barcode") String barcode) {
        Token token = new Token(tokenStr);
        ServiceResult result = securedCopyService.addDiscCopy(copyWithOwner, barcode, token);
        return Response.ok(result.getStatus()).status(result.getCode()).build();
    }
    /**
     * Receives a request for returning a disc to the database.
     * @param barcode The barcode number of the disc of which a copy is to be returned.
     * @return The response containing a status code and message.
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{barcode}")
    public Response returnBook(@PathParam("barcode") String barcode) {
        Token token = new Token(tokenStr);
        ServiceResult result = securedCopyService.returnDisc(barcode, token);
        return Response.ok(result.getStatus()).status(result.getCode()).build();
    }
}
