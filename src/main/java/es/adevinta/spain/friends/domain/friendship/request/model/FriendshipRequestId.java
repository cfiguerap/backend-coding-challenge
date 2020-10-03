package es.adevinta.spain.friends.domain.friendship.request.model;

import es.adevinta.spain.friends.domain.common.AbstractDomainId;
import es.adevinta.spain.friends.domain.user.model.vo.Username;

public class FriendshipRequestId extends AbstractDomainId {

    protected FriendshipRequestId() {}

    private FriendshipRequestId(String value) {
        super(value);
    }

    public static FriendshipRequestId create(Username from, Username to) {
        return new FriendshipRequestId(from.value() + "." + to.value());
    }
}