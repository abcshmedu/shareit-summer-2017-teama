package edu.hm.shareit.models.authentication;

/**
 * Wrapper Class for the String Token used for Authentication
 */
public class Token {
    String token;

    //Default Constructor
    public Token(){
        this(null);
    }

    /**
     * Helper Constructor
     *
     * @param token the string token
     */
    public Token(String token){
        setToken(token);
    }

    /**
     * Getter for the token string
     * @return The token string
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter for the token string
     * @param token The token string
     */
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return getToken();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Token otherToken = (Token) o;

        return token.equals(otherToken.getToken());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}
