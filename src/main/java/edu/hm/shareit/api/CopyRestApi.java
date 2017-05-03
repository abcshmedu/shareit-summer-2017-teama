package edu.hm.shareit.api;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.resources.MediaService;
import edu.hm.shareit.resources.MediaServiceImpl;
import edu.hm.shareit.resources.MediaServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Nelson on 03.05.2017.
 */
@Path("copies")
public class CopyRestApi  {

    private static MediaService MEDIA_SERVICE;

    public CopyRestApi() {
        setMediaService(new MediaServiceImpl());
    }



    @POST
    @Path("{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBookCopy(Copy bookCopy) {
        MediaServiceResult result = MEDIA_SERVICE.addBookCopy(bookCopy);
        return Response.ok(result.getStatus()).status(result.getCode()).build();
    }

    private void setMediaService(MediaServiceImpl mediaService) {
        MEDIA_SERVICE = mediaService;
    }
}
