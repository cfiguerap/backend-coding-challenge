package es.adevinta.spain.friends.domain.friendship.common.model;

import es.adevinta.spain.friends.UserTestUtils;
import es.adevinta.spain.friends.domain.user.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FriendshipBuilderTest {

    @Test
    public void shouldBuilderFriendshipsSuccessfully() {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();
        Friendship friendship = FriendshipBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
            .build();
        assertEquals(userFrom, friendship.from());
        assertEquals(userTo, friendship.to());
    }
}
