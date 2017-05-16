package edu.hm.shareit.models.authentication;

public class User {
    String username;
    String password;

    public User(){
        this(null, null);
    }

    public User(String username, String password){
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
