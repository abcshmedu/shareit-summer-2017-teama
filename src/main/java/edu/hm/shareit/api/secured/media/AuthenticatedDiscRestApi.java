package edu.hm.shareit.api.secured.media;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.resources.ServiceResult;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;
import edu.hm.shareit.resources.secured.media.SecuredMediaService;
import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * REST Resource Class for the Authenticated Disc API.
 *
 * GET  /discs/{barcode}->  getDisc
 * GET  /discs          ->  getDiscs
 * POST /discs          ->  postDisc
 * PUT  /discs/{barcode}->  updateDisc
 */
@Path("discs")
public class AuthenticatedDiscRestApi {
    private SecuredMediaService securedMediaService = ServiceGetter.getSecuredMediaService();

    @HeaderParam(HttpHeaders.AUTHORIZATION)
    private String tokenStr = null;

    /**
     * GET (getDisc) Returns a specific disc, provided it exists.
     * @param barcode The barcode for the book to get
     * @return The Response with the Json format for the disc
     */
    @GET
    @Path("{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode)  {
        Token token = new Token(tokenStr);
        ServiceResult result = securedMediaService.getDisc(barcode, token);
        if(result instanceof AuthenticationServiceResult){
            return Response.ok(result.getStatus()).status(result.getCode()).build();
        }else{
            MediaServiceResult mediaRes = (MediaServiceResult) result;
            Collection collection = mediaRes.getMedia();
            Disc disc = null;
            if(collection != null){
                disc = (Disc) collection.toArray()[0];
            }
            return Response.ok(disc).build();
        }
    }

    /**
     * GET (getDiscs) Returns all discs.
     * @return The Response with the Json array format for the discs
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() {
        Token token = new Token(tokenStr);
        ServiceResult result = securedMediaService.getDiscs(token);
        if(result instanceof AuthenticationServiceResult){
            return Response.ok(result.getStatus()).status(result.getCode()).build();
        }else{
            MediaServiceResult mediaRes = (MediaServiceResult) result;
            Collection collection = mediaRes.getMedia();
            return Response.ok(collection).build();
        }
    }

    /**
     * POST (postDisc) Posts a disc to the Media_Service.
     * @param disc The disc to post
     * @return The Response from the SecuredMediaService
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postDisc(Disc disc) {
        Token token = new Token(tokenStr);
        ServiceResult result = securedMediaService.addDisc(disc, token);
        return Response.ok(result).status(result.getCode()).build();
    }

    /**
     * PUT (updateDisc) Updates a disc with the given barcode to the new values.
     * @param disc The disc with the new values
     * @param barcode The barcode for the disc to replace
     * @return The Response from the SecuredMediaService
     */
    @PUT
    @Path("{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, @PathParam("barcode") String barcode)  {
        Token token = new Token(tokenStr);
        ServiceResult result = securedMediaService.updateDisc(disc, barcode, token);
        return Response.ok(result).status(result.getCode()).build();
    }
}
