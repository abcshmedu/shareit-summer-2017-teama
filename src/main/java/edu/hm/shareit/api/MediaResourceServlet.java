package edu.hm.shareit.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;
import edu.hm.shareit.resources.MediaService;
import edu.hm.shareit.resources.MediaServiceImpl;
import edu.hm.shareit.resources.MediaServiceResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet(name = "MediaResourceServlet")
public class MediaResourceServlet extends HttpServlet {
    private static final String BOOKS_ROOT_URI = "/books";
    private static final String DISCS_ROOT_URI = "/discs";
    private static final MediaService MEDIA_SERVICE = new MediaServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonRequest = getBodyContent(request);
        ObjectMapper mapper = new ObjectMapper();
        MediaServiceResult result;
        if (BOOKS_ROOT_URI.equals(request.getPathInfo())) {
            try{
                Book book = mapper.readValue(jsonRequest, Book.class);
                result = MEDIA_SERVICE.addBook(book);
            } catch (Exception ex){
                result = MediaServiceResult.INVALID_JSON_FORMAT;
            }
        } else if (DISCS_ROOT_URI.equals(request.getPathInfo())) {
            try{
                Disc disc = mapper.readValue(jsonRequest, Disc.class);
                result = MEDIA_SERVICE.addDisc(disc);
            } catch (Exception ex){
                result = MediaServiceResult.INVALID_JSON_FORMAT;
            }
        } else {
            result = MediaServiceResult.INVALID_URL_REQUEST;
        }

        response.setStatus(result.getCode());

        if(result.getCode()/100 != 2){
            String jsonErrorResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            try(PrintWriter out = response.getWriter()){
                out.println(jsonErrorResponse);
            }
        }


    }

    private String getBodyContent(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        String jsonRequest = "";
        for (String line = reader.readLine(); line != null; line = reader.readLine())
            jsonRequest += line;
        return jsonRequest;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(BOOKS_ROOT_URI.equals(request.getPathInfo())) {
            Collection<? extends Medium> books = MEDIA_SERVICE.getBooks();

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(books);
            try(PrintWriter out = response.getWriter()){
                out.println(jsonResponse);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
