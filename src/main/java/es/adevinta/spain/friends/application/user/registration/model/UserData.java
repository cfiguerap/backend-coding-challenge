package es.adevinta.spain.friends.application.user.registration.model;

public class UserData {

    private String username;
    private String password;

    UserData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }
}
