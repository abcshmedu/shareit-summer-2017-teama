package edu.hm.shareit.resources.secured.authentication;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.authentication.User;

/**
 * AuthenticationService interface.
 */
public interface AuthenticationService {

    /**
     * Login method.
     * @param user the user to be logged in
     * @return the result
     */
    AuthenticationServiceResult login(User user);

    /**
     * Authenticate method.
     * @param token the token for authentication
     * @return the result
     */
    AuthenticationServiceResult authenticate(Token token);
}
