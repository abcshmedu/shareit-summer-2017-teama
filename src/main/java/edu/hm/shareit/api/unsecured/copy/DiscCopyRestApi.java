package edu.hm.shareit.api.unsecured.copy;

import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.resources.unsecured.copy.CopyService;
import edu.hm.shareit.resources.unsecured.copy.CopyServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
/**
* Rest API for disc copies.
* Receives requests for adding copies of discs to the database.
 */
@Path("discs")
public class DiscCopyRestApi {
    private CopyService copyService = ServiceGetter.getCopyService();

    /**
     * Shows a list of all disc copies that exist in the database.
     * @return List of disc copies
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listDiscCopies() {
        Collection<Copy> result = copyService.listDiscCopies().getCopies();
        return Response.ok(result).build();
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
        CopyServiceResult result = copyService.borrowDisc(barcode);
        return Response.ok(result).build();
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
        System.out.println("got isbn: " + barcode + "\n" + "and got owner: " + copyWithOwner.getOwner());
        CopyServiceResult result = copyService.addDiscCopy(copyWithOwner, barcode);
        return Response.ok(result).build();
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
        CopyServiceResult result = copyService.returnDisc(barcode);
        return Response.ok(result).build();
    }
}
