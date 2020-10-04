package es.adevinta.spain.friends.application.friendship.request.model;

public class FriendshipData {

    private String from;
    private String password;

    FriendshipData(String from, String password) {
        this.from = from;
        this.password = password;
    }

    public String from() {
        return from;
    }

    public String password() {
        return password;
    }
}
