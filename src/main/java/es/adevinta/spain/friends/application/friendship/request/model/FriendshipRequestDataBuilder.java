package es.adevinta.spain.friends.application.friendship.request.model;

public class FriendshipRequestDataBuilder {

    private String from;
    private String password;
    private String to;

    public static FriendshipRequestDataBuilder builder() {
        return new FriendshipRequestDataBuilder();
    }

    public FriendshipRequestDataBuilder withFrom(String from) {
        this.from = from;
        return this;
    }

    public FriendshipRequestDataBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public FriendshipRequestDataBuilder withTo(String to) {
        this.to = to;
        return this;
    }

    public FriendshipRequestData build() {
        return new FriendshipRequestData(from, password, to);
    }
}