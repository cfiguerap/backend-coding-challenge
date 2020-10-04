package es.adevinta.spain.friends.domain.friendship.request.model;

import es.adevinta.spain.friends.UserTestUtils;
import es.adevinta.spain.friends.domain.user.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FriendshipRequestBuilderTest {

    @Test
    public void shouldBuilderFriendshipRequestsSuccessfully() {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();
        FriendshipRequest friendshipRequest = FriendshipRequestBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
            .build();
        assertEquals(userFrom, friendshipRequest.from());
        assertEquals(userTo, friendshipRequest.to());
    }
}
