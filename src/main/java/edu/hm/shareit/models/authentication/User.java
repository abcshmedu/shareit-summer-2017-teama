package edu.hm.shareit.models.authentication;

/**
 * Simple User Class
 *
 * A user has a username and password
 */
public class User {
    String username;
    String password;

    //Default constructor
    public User(){
        this(null, null);
    }

    /**
     * Helper Constructor
     * @param username The users name
     * @param password The users password
     */
    public User(String username, String password){
        setUsername(username);
        setPassword(password);
    }

    /**
     * Getter for the users name
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for the users password
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the users name
     * @param username The new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter for the users password
     * @param password The new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return"User:{username: " + username + ", password: " + password + "}";
    }

    @Override
    public boolean equals(Object o) {
        System.out.println(this);
        System.out.println(":equals:");
        System.out.println(o);
        if (this == o) {
            System.out.println("TRUE");
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            System.out.println("FALSE");
            return false;
        }

        User user = (User) o;

        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
