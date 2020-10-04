package es.adevinta.spain.friends.domain.friendship.common.model;

import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.user.model.User;

public class FriendshipBuilder {

    private User from;
    private User to;

    public static FriendshipBuilder builder() {
        return new FriendshipBuilder();
    }

    public FriendshipBuilder withFrom(User from) {
        this.from = from;
        return this;
    }

    public FriendshipBuilder withTo(User to) {
        this.to = to;
        return this;
    }

    public FriendshipBuilder withFriendshipRequest(FriendshipRequest friendshipRequest) {
        this.from = friendshipRequest.from();
        this.to = friendshipRequest.to();
        return this;
    }

    public Friendship build() {
        return new Friendship(from, to);
    }
}