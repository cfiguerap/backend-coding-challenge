package es.adevinta.spain.friends.domain.friendship.common.model;

import es.adevinta.spain.friends.domain.common.AbstractDomainId;
import es.adevinta.spain.friends.domain.user.model.vo.Username;

public class FriendshipId extends AbstractDomainId {

    protected FriendshipId() {}

    private FriendshipId(String value) {
        super(value);
    }

    public static FriendshipId create(Username from, Username to) {
        return new FriendshipId(from.value() + "." + to.value());
    }
}