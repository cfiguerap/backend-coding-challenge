package es.adevinta.spain.friends.infra.friendship.request.model;

import es.adevinta.spain.friends.infra.friendship.common.model.FriendshipEntity;
import es.adevinta.spain.friends.infra.user.model.UserEntity;

public class FriendshipRequestEntity extends FriendshipEntity {

    public FriendshipRequestEntity(UserEntity from, UserEntity to) {
        super(from, to);
    }
}
