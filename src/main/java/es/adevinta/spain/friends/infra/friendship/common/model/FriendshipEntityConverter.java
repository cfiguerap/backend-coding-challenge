package es.adevinta.spain.friends.infra.friendship.common.model;

import es.adevinta.spain.friends.domain.friendship.common.model.Friendship;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequestBuilder;
import es.adevinta.spain.friends.infra.common.EntityConverter;
import es.adevinta.spain.friends.infra.friendship.request.model.FriendshipRequestEntity;
import es.adevinta.spain.friends.infra.user.model.UserEntity;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FriendshipEntityConverter extends EntityConverter<Friendship, FriendshipEntity> {

    private final UserEntityConverter userEntityConverter;

    @Autowired
    public FriendshipEntityConverter(UserEntityConverter userEntityConverter) {
        this.userEntityConverter = userEntityConverter;
    }

    @Override
    public FriendshipEntity target(Friendship t) {
        UserEntity from = userEntityConverter.target(t.from());
        UserEntity to = userEntityConverter.target(t.to());
        return new FriendshipRequestEntity(from, to);
    }

    @Override
    public Friendship source(FriendshipEntity r) {
        return FriendshipRequestBuilder.builder()
                .withFrom(userEntityConverter.source(r.from()))
                .withTo(userEntityConverter.source(r.to()))
            .build();
    }
}
