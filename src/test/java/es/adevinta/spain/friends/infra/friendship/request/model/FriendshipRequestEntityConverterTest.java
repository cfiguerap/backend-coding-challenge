package es.adevinta.spain.friends.infra.friendship.request.model;

import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequestBuilder;
import es.adevinta.spain.friends.domain.user.UserTestUtils;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.infra.friendship.common.model.FriendshipEntityConverter;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FriendshipRequestEntityConverterTest {

    private final UserEntityConverter userEntityConverter = new UserEntityConverter();
    private final FriendshipEntityConverter friendshipEntityConverter = new FriendshipEntityConverter(userEntityConverter);
    private final FriendshipRequestEntityConverter converter = new FriendshipRequestEntityConverter(friendshipEntityConverter);

    @Test
    public void shouldConvertFriendshipRequestToEntitySuccessfully() {
        User user1 = UserTestUtils.randomUser();
        User user2 = UserTestUtils.randomUser();
        FriendshipRequest request = FriendshipRequestBuilder.builder()
                .withFrom(user1)
                .withTo(user2)
            .build();
        FriendshipRequestEntity entity = converter.target(request);

        assertNotNull(entity);
        assertEquals(user1.username().value(), entity.from().username());
        assertEquals(user2.username().value(), entity.to().username());
    }

    @Test
    public void shouldConvertEntityToFriendshipRequestSuccessfully() {
        User user1 = UserTestUtils.randomUser();
        User user2 = UserTestUtils.randomUser();
        FriendshipRequestEntity entity = new FriendshipRequestEntity(userEntityConverter.target(user1), userEntityConverter.target(user2));
        FriendshipRequest request = converter.source(entity);
        assertNotNull(request);
        assertEquals(user1.username(), request.from().username());
        assertEquals(user2.username(), request.to().username());
    }
}
