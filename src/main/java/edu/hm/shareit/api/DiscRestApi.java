package edu.hm.shareit.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;
import edu.hm.shareit.resources.MediaService;
import edu.hm.shareit.resources.MediaServiceImpl;
import edu.hm.shareit.resources.MediaServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Collection;

@Path("discs")
public class DiscRestApi {
    private final ObjectMapper mapper = new ObjectMapper();

    @Context
    private static MediaService MEDIA_SERVICE;

    @GET
    @Path("{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode) throws JsonProcessingException {
        Book book = MEDIA_SERVICE.getBook(barcode);
        String result = mapper.writeValueAsString(book);
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() throws IOException {
        Collection<? extends Medium> discs = MEDIA_SERVICE.getDiscs();
        String responseStr = mapper.writeValueAsString(discs);
        return Response.ok(responseStr, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postDisc(String jsonBody) throws IOException {
        Disc disc = mapper.readValue(jsonBody.getBytes(), Disc.class);
        MediaServiceResult result = MEDIA_SERVICE.addDisc(disc);
        String jsonResult = mapper.writeValueAsString(result);
        return Response.ok(jsonResult, MediaType.APPLICATION_JSON).status(result.getCode()).build();
    }

    @PUT
    @Path("{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDisc(@PathParam("barcode") String barcode, String jsonBody) throws IOException {
        Disc updateValues = mapper.readValue(jsonBody.getBytes(), Disc.class);
        Disc fullInstance = new Disc(updateValues.getTitle(), barcode, updateValues.getDirector(), updateValues.getFsk());
        MediaServiceResult result = MEDIA_SERVICE.updateDisc(fullInstance);
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).status(result.getCode()).build();
    }
}
