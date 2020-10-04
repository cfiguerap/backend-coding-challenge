package es.adevinta.spain.friends.application.friendship.request.model;

public class FriendshipDataBuilder {

    private String from;
    private String password;

    public static FriendshipDataBuilder builder() {
        return new FriendshipDataBuilder();
    }

    public FriendshipDataBuilder withFrom(String from) {
        this.from = from;
        return this;
    }

    public FriendshipDataBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public FriendshipData build() {
        return new FriendshipData(from, password);
    }
}