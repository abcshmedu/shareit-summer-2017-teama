package edu.hm.shareit.resources.secured.authentication;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.authentication.User;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationServiceImpl implements AuthenticationService{
    private final Map<User, Token> userToToken = new HashMap<>();

    private static final String SALT = "0xw3";


    /**
     * Static Users: Admin, Sally, and Bob
     */

    public AuthenticationServiceImpl(){
        addUser(new User("admin", "admin"));

        addUser(new User("sally", "password"));

        addUser(new User("bob", "123456"));
    }

    public AuthenticationServiceResult login(User user){
        AuthenticationServiceResult result = AuthenticationServiceResult.LOGIN_REJECTED;
        for(User aUser : userToToken.keySet()){
            if(aUser.equals(user)){
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
        for(Token aToken : userToToken.values()){
            if(aToken.equals(token)){
                result = AuthenticationServiceResult.AUTHENTICATED;
                result.setToken(token);
                return result;
            }
        }
        return result;
    }

    private static Token generateToken(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String time = System.currentTimeMillis() + "";
        String saltedPasswordAndTime = SALT + password + time;
        String saltedPasswordHash = Math.abs(saltedPasswordAndTime.hashCode()) + "";
        String totalTokenStr = username + "-" + saltedPasswordHash + "-" + username;
        return new Token(totalTokenStr);
    }

    private void addUser(User user){
        Token token = generateToken(user);
        userToToken.put(user, token);
    }
}
