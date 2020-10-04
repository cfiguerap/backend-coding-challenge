package es.adevinta.spain.friends.application.friendship.request.model;

public class FriendshipRequestData {

    private final String from;
    private final String password;
    private final String to;

    FriendshipRequestData(String from, String password, String to) {
        this.from = from;
        this.password = password;
        this.to = to;
    }

    public String from() {
        return from;
    }

    public String password() {
        return password;
    }

    public String to() {
        return to;
    }
}
