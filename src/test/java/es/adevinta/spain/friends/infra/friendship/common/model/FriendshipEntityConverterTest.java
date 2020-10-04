package es.adevinta.spain.friends.infra.friendship.common.model;

import es.adevinta.spain.friends.domain.friendship.common.model.Friendship;
import es.adevinta.spain.friends.domain.friendship.common.model.FriendshipBuilder;
import es.adevinta.spain.friends.domain.user.UserTestUtils;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FriendshipEntityConverterTest {

    private UserEntityConverter userEntityConverter = new UserEntityConverter();
    private FriendshipEntityConverter converter = new FriendshipEntityConverter(userEntityConverter);

    @Test
    public void shouldConvertFriendshipRequestToEntitySuccessfully() {
        User user1 = UserTestUtils.randomUser();
        User user2 = UserTestUtils.randomUser();
        Friendship request = FriendshipBuilder.builder()
                .withFrom(user1)
                .withTo(user2)
            .build();
        FriendshipEntity entity = converter.target(request);

        assertNotNull(entity);
        assertEquals(user1.username().value(), entity.from().username());
        assertEquals(user2.username().value(), entity.to().username());
    }

    @Test
    public void shouldConvertEntityToFriendshipRequestSuccessfully() {
        User user1 = UserTestUtils.randomUser();
        User user2 = UserTestUtils.randomUser();
        FriendshipEntity entity = new FriendshipEntity(userEntityConverter.target(user1), userEntityConverter.target(user2));
        Friendship request = converter.source(entity);
        assertNotNull(request);
        assertEquals(user1.username(), request.from().username());
        assertEquals(user2.username(), request.to().username());
    }
}
