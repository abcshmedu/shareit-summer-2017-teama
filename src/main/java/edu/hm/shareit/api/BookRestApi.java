package edu.hm.shareit.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Medium;
import edu.hm.shareit.resources.MediaService;
import edu.hm.shareit.resources.MediaServiceImpl;
import edu.hm.shareit.resources.MediaServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Collection;

@Path("shareit/media")
public class BookRestApi {
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String BOOKS_ROOT_URI = "books";
    private static final MediaService MEDIA_SERVICE = new MediaServiceImpl();

    @GET
    @Path(BOOKS_ROOT_URI + "/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) throws JsonProcessingException {
        Book book = MEDIA_SERVICE.getBook(isbn);
        String result = mapper.writeValueAsString(book);
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    @Path(BOOKS_ROOT_URI)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() throws JsonProcessingException{
        Collection<? extends Medium> books = MEDIA_SERVICE.getBooks();

        //System.out.println("Books size after fetch: " + books.size());

        String responseStr = mapper.writeValueAsString(books);
        return Response.ok(responseStr, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path(BOOKS_ROOT_URI)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postBook(String jsonBody) throws IOException {
        Book book = mapper.readValue(jsonBody.getBytes(), Book.class);
        MediaServiceResult result = MEDIA_SERVICE.addBook(book);
        String jsonResult = mapper.writeValueAsString(result);

        //System.out.println("New book json:" + jsonBody + " and status code: " + result.getCode());

        return Response.ok(jsonResult, MediaType.APPLICATION_JSON).status(result.getCode()).build();
    }

    @Path("/books/{isbn}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("isbn") String isbn, String jsonBody) throws IOException {
        Book updateValues = mapper.readValue(jsonBody.getBytes(), Book.class);
        Book fullInstance = new Book(updateValues.getTitle(), isbn, updateValues.getAuthor());
        MediaServiceResult result = MEDIA_SERVICE.updateBook(fullInstance);
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).status(result.getCode()).build();
    }
}
