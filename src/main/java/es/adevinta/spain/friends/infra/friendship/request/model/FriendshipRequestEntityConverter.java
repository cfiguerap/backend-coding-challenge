package es.adevinta.spain.friends.infra.friendship.request.model;

import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.infra.common.EntityConverter;
import es.adevinta.spain.friends.infra.friendship.common.model.FriendshipEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FriendshipRequestEntityConverter extends EntityConverter<FriendshipRequest, FriendshipRequestEntity> {

    private FriendshipEntityConverter friendshipEntityConverter;

    @Autowired
    public FriendshipRequestEntityConverter(FriendshipEntityConverter friendshipEntityConverter) {
        this.friendshipEntityConverter = friendshipEntityConverter;
    }

    @Override
    public FriendshipRequestEntity target(FriendshipRequest t) {
        return (FriendshipRequestEntity) friendshipEntityConverter.target(t);
    }

    @Override
    public FriendshipRequest source(FriendshipRequestEntity r) {
        return (FriendshipRequest) friendshipEntityConverter.source(r);
    }
}
