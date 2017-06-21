package edu.hm.shareit.resources.secured.authentication;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.authentication.User;

/**
 * Created by Sascha on 16.06.17.
 */
public class AuthenticationServiceMock implements AuthenticationService {
    @Override
    public AuthenticationServiceResult login(User user) {
        return AuthenticationServiceResult.LOGIN_ACCEPTED;
    }

    @Override
    public AuthenticationServiceResult authenticate(Token token) {
        return AuthenticationServiceResult.TOKEN_NOT_VALID;
    }
}
