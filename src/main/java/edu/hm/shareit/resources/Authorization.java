package edu.hm.shareit.resources;

import edu.hm.shareit.models.authentication.Token;

import javax.ws.rs.core.Response;

public interface Authorization {

    Response authorize(Token token);
}
