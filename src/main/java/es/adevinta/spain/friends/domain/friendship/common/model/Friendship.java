package es.adevinta.spain.friends.domain.friendship.common.model;

import es.adevinta.spain.friends.domain.common.AbstractDomainObject;
import es.adevinta.spain.friends.domain.user.model.User;

public class Friendship extends AbstractDomainObject<FriendshipId> {

    private final User from;
    private final User to;

    public Friendship(User from, User to) {
        super(FriendshipId.create(from.username(), to.username()));
        this.from = from;
        this.to = to;
    }

    public User from() {
        return from;
    }

    public User to() {
        return to;
    }

    public Friendship reverse() {
        return FriendshipBuilder.builder()
                .withFrom(to)
                .withTo(from)
            .build();
    }
}