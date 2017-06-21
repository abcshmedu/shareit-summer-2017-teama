package edu.hm.shareit.businesslogic.secured.authentication;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.authentication.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Authentication Service Implementation for Authentication and Authorization of users.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    /**
     * Salt string
     */
    private static final String SALT = "0xw3";

    /**
     * User to Token mapping (not integrated into the database)
     */
    private final Map<User, Token> userToToken = new HashMap<>();


    /**
     * Static Users: Admin, Sally, and Bob.
     */

    public AuthenticationServiceImpl() {
        addUser(new User("admin", "admin"));

        addUser(new User("sally", "password"));

        addUser(new User("bob", "123456"));
    }

    @Override
    public AuthenticationServiceResult login(User user) {
        AuthenticationServiceResult result = AuthenticationServiceResult.LOGIN_REJECTED;
        for (User aUser : userToToken.keySet()) {
            if (aUser.equals(user)) {
                Token userToken = userToToken.get(aUser);
                result = AuthenticationServiceResult.LOGIN_ACCEPTED;
                result.setToken(userToken);
                return result;
            }
        }
        return result;
    }

    @Override
    public AuthenticationServiceResult authenticate(Token token) {
        AuthenticationServiceResult result = AuthenticationServiceResult.TOKEN_NOT_VALID;
        for (Token aToken : userToToken.values()) {
            if (aToken.equals(token)) {
                result = AuthenticationServiceResult.AUTHENTICATED;
                result.setToken(token);
                return result;
            }
        }
        return result;
    }

    /*
        private static Token generateToken(User user){
            String username = user.getUsername();
            String password = user.getPassword();
            String time = System.currentTimeMillis() + "";
            String saltedPasswordAndTime = SALT + password + time;
            String saltedPasswordHash = Math.abs(saltedPasswordAndTime.hashCode()) + "";
            String totalTokenStr = username + "-" + saltedPasswordHash + "-" + username;
            return new Token(totalTokenStr);
        }
    */

    /**
     * adding Users to Map for initialization.
     * @param user the user to be added
     */
    // ToDo Tokens are hard-coded!!
    private void addUser(User user) {
        //Token token = generateToken(user);
        Token token = new Token(user.getUsername() + "-82955211-" + user.getUsername());
        userToToken.put(user, token);
    }
}
