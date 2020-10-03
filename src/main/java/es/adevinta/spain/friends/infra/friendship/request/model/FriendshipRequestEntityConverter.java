package es.adevinta.spain.friends.infra.friendship.request.model;

import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequestBuilder;
import es.adevinta.spain.friends.infra.common.EntityConverter;
import es.adevinta.spain.friends.infra.user.model.UserEntity;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FriendshipRequestEntityConverter extends EntityConverter<FriendshipRequest, FriendshipRequestEntity> {

    private UserEntityConverter userEntityConverter;

    @Autowired
    public FriendshipRequestEntityConverter(UserEntityConverter userEntityConverter) {
        this.userEntityConverter = userEntityConverter;
    }

    @Override
    public FriendshipRequestEntity target(FriendshipRequest t) {
        UserEntity from = userEntityConverter.target(t.from());
        UserEntity to = userEntityConverter.target(t.to());
        return new FriendshipRequestEntity(from, to);
    }

    @Override
    public FriendshipRequest source(FriendshipRequestEntity r) {
        return FriendshipRequestBuilder.builder()
                .withFrom(userEntityConverter.source(r.from()))
                .withTo(userEntityConverter.source(r.to()))
            .build();
    }
}
