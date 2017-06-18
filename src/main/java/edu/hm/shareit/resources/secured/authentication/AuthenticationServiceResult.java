package edu.hm.shareit.resources.secured.authentication;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.resources.ServiceResult;

/**
 * Contains all the status codes and messages needed for the CopyService.
 */
public enum AuthenticationServiceResult implements ServiceResult {

    AUTHENTICATED(200, "Authenticated"),
    LOGIN_ACCEPTED(201, "Login Accepted"),
    TOKEN_NOT_VALID(400, "Token not valid"),
    LOGIN_REJECTED(401, "Login Rejected");

    private int code;
    private String status;
    private Token token;
    private static final int ERROR_404 = 404;


    /**
     * Custom constructor.
     */
    AuthenticationServiceResult() {
        this(ERROR_404, null);
    }

    /**
     * Custom constructor.
     *
     * @param code   the status code.
     * @param status the status message.
     */
    AuthenticationServiceResult(int code, String status) {
        setCode(code);
        setStatus(status);
        setToken(null);
    }

    /**
     * Getter for the status code.
     *
     * @return the status code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Getter for the status message.
     *
     * @return the status message.
     */
    public String getStatus() {
        if (token != null) {
            return status + ":" + token.getToken();
        } else {
            return status;
        }
    }

    /**
     * Setter for the status code.
     *
     * @param code the status code.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * setter for the status message.
     *
     * @param status the status message.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * getter for token.
     * @return the token to be retrieved.
     */
    public Token getToken() {
        return token;
    }

    /**
     * setter for token.
     * @param token the token to be set.
     */
    public void setToken(Token token) {
        this.token = token;
    }
}