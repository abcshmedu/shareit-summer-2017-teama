package edu.hm.shareit.api.exception_handlers;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception Handler for UnrecognizedPropertyException.
 */
@Provider
public class UnrecognizedPropertyExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException> {

    @Override
    public Response toResponse(UnrecognizedPropertyException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("This is an invalid request. The field " + exception.getPropertyName() + " is not recognized by the system.")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

}
