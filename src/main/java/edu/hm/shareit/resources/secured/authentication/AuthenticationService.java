package edu.hm.shareit.resources.secured.authentication;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.authentication.User;

public interface AuthenticationService {

    AuthenticationServiceResult login(User user);

    AuthenticationServiceResult authenticate(Token token);
}
