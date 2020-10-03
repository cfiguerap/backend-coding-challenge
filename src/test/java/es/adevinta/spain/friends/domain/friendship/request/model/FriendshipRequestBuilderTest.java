package es.adevinta.spain.friends.domain.friendship.request.model;

import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.infra.user.UserUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FriendshipRequestBuilderTest {

    @Test
    public void shouldBuilderCreateUsersSuccessfully() {
        User userFrom = UserUtils.randomUser();
        User userTo = UserUtils.randomUser();
        FriendshipRequest friendshipRequest = FriendshipRequestBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
            .build();
        assertEquals(userFrom, friendshipRequest.from());
        assertEquals(userTo, friendshipRequest.to());
    }
}
