package es.adevinta.spain.friends.infra.friendship.request.model;

import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequestBuilder;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.infra.user.UserUtils;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FriendshipRequestEntityConverterTest {

    private UserEntityConverter userEntityConverter = new UserEntityConverter();
    private FriendshipRequestEntityConverter converter = new FriendshipRequestEntityConverter(userEntityConverter);

    @Test
    public void shouldConvertFriendshipRequestToEntitySuccessfully() {
        User user1 = UserUtils.randomUser();
        User user2 = UserUtils.randomUser();
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
        User user1 = UserUtils.randomUser();
        User user2 = UserUtils.randomUser();
        FriendshipRequestEntity entity = new FriendshipRequestEntity(userEntityConverter.target(user1), userEntityConverter.target(user2));
        FriendshipRequest request = converter.source(entity);
        assertNotNull(request);
        assertEquals(user1.username(), request.from().username());
        assertEquals(user2.username(), request.to().username());
    }
}
