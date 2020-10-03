package es.adevinta.spain.friends.domain.friendship.request.model;

import es.adevinta.spain.friends.domain.user.model.User;

public class FriendshipRequestBuilder {

    private User from;
    private User to;

    public static FriendshipRequestBuilder builder() {
        return new FriendshipRequestBuilder();
    }

    public FriendshipRequestBuilder withFrom(User from) {
        this.from = from;
        return this;
    }

    public FriendshipRequestBuilder withTo(User to) {
        this.to = to;
        return this;
    }

    public FriendshipRequest build() {
        return new FriendshipRequest(from, to);
    }
}