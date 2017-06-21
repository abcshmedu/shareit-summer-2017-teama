package edu.hm.shareit.restapi.unsecured.media;

import edu.hm.shareit.businesslogic.unsecured.media.MediaService;
import edu.hm.shareit.businesslogic.unsecured.media.MediaServiceResult;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * REST Resource Class for the Disc API.
 * <p>
 * GET  /discs/{barcode}->  getDisc
 * GET  /discs          ->  getDiscs
 * POST /discs          ->  postDisc
 * PUT  /discs/{barcode}->  updateDisc
 */
@Path("discs")
public class DiscRestApi {

    @Inject
    private MediaService mediaService;

    /**
     * GET (getDisc) Returns a specific disc, provided it exists.
     *
     * @param barcode The barcode for the book to get
     * @return The Response with the Json format for the disc
     */
    @GET
    @Path("{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode) {
        Collection collection = mediaService.getDisc(barcode).getMedia();
        Disc disc = null;
        if (collection != null) {
            disc = (Disc) collection.toArray()[0];
        }
        return Response.ok(disc).build();
    }

    /**
     * GET (getDiscs) Returns all discs.
     *
     * @return The Response with the Json array format for the discs
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() {
        Collection< ? extends Medium> discs = mediaService.getDiscs().getMedia();
        return Response.ok(discs).build();
    }

    /**
     * POST (postDisc) Posts a disc to the Media_Service.
     *
     * @param disc The disc to post
     * @return The Response from the SecuredMediaService
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postDisc(Disc disc) {
        MediaServiceResult result = mediaService.addDisc(disc);
        return Response.ok(result).status(result.getCode()).build();
    }

    /**
     * PUT (updateDisc) Updates a disc with the given barcode to the new values.
     *
     * @param disc    The disc with the new values
     * @param barcode The barcode for the disc to replace
     * @return The Response from the SecuredMediaService
     */
    @PUT
    @Path("{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, @PathParam("barcode") String barcode) {
        MediaServiceResult result = mediaService.updateDisc(disc, barcode);
        return Response.ok(result).status(result.getCode()).build();
    }
}
