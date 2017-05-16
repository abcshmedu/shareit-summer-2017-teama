package edu.hm.shareit.resources.authentication;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.authentication.User;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationServiceImpl implements AuthenticationService{
    private final Map<String, User> tokenToUser = new HashMap<>();
    private final Map<String, Token> userToToken = new HashMap<>();


    /**
     * Static Users: Admin, Sally, and Bob
     */

    public AuthenticationServiceImpl(){
        User user = new User("admin", "admin");
        Token token = generateToken(user);
        tokenToUser.put(token.toString(), user);
        userToToken.put(user.toString(), token);
        user = new User("sally", "password");
        token = generateToken(user);
        tokenToUser.put(token.toString(), user);
        userToToken.put(user.toString(), token);
        user = new User("bob", "123456");
        token = generateToken(user);
        tokenToUser.put(token.toString(), user);
        userToToken.put(user.toString(), token);
    }

    public AuthenticationServiceResult login(User user){
        AuthenticationServiceResult result;
        if(userToToken.containsKey(user.toString())){
            Token userToken = userToToken.get(user.toString());
            result = AuthenticationServiceResult.LOGIN_ACCEPTED;
            result.setToken(userToken);
        }else{
            result = AuthenticationServiceResult.LOGIN_REJECTED;
        }
        return result;
    }

    @Override
    public AuthenticationServiceResult authenticate(Token token) {
        return AuthenticationServiceResult.AUTHENTICATED;
    }

    private static Token generateToken(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String tokenPtOne = username.hashCode() + "";
        String tokenPtTwo = password.hashCode() + "";
        String tokenPtThree = user.hashCode() + "";
        String tokenPtFour =  tokenPtOne.hashCode() + "" + tokenPtTwo.hashCode() + "" + tokenPtThree.hashCode() + "";
        String totalTokenStr = username + "-" + tokenPtOne + "" + tokenPtTwo + "" + tokenPtThree + "" + tokenPtFour + "-" + username;
        return new Token(totalTokenStr);
    }
}
