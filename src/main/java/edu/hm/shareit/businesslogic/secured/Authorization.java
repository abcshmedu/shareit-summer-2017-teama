package edu.hm.shareit.businesslogic.secured;

import edu.hm.shareit.businesslogic.secured.authentication.AuthenticationServiceResult;
import edu.hm.shareit.models.authentication.Token;

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
