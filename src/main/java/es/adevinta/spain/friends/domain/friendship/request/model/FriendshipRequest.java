package es.adevinta.spain.friends.domain.friendship.request.model;

import es.adevinta.spain.friends.domain.friendship.common.model.Friendship;
import es.adevinta.spain.friends.domain.user.model.User;

public class FriendshipRequest extends Friendship {

    public FriendshipRequest(User from, User to) {
        super(from, to);
    }

    @Override
    public FriendshipRequest reverse() {
        return FriendshipRequestBuilder.builder()
                .withFrom(to())
                .withTo(from())
            .build();
    }
}