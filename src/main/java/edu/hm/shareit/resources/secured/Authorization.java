package edu.hm.shareit.resources.secured;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;

public interface Authorization {
    public AuthenticationServiceResult authorize(Token token);
}
