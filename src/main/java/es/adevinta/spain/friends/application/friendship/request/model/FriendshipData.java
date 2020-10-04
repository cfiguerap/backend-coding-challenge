package es.adevinta.spain.friends.application.friendship.request.model;

public class FriendshipData {

    private final String from;
    private final String password;

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
