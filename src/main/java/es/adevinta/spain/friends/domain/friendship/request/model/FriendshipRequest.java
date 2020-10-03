package es.adevinta.spain.friends.domain.friendship.request.model;

import es.adevinta.spain.friends.domain.common.AbstractDomainObject;
import es.adevinta.spain.friends.domain.user.model.User;

public class FriendshipRequest extends AbstractDomainObject<FriendshipRequestId> {

    private User from;
    private User to;

    public FriendshipRequest(User from, User to) {
        super(FriendshipRequestId.create(from.username(), to.username()));
        this.from = from;
        this.to = to;
    }

    public User from() {
        return from;
    }

    public User to() {
        return to;
    }
}