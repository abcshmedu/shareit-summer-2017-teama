package edu.hm.shareit.resources.secured;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;

/**
 * Authorization interface.
 */
public interface Authorization {
    /**
     * Authorization method to authorize token.
     * @param token token to be authorized
     * @return result of authorization
     */
    AuthenticationServiceResult authorize(Token token);
}
