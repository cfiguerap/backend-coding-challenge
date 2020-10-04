package es.adevinta.spain.friends.application.friendship.common;

import es.adevinta.spain.friends.application.friendship.request.model.FriendshipData;
import es.adevinta.spain.friends.application.friendship.request.model.FriendshipDataBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FriendshipDataBuilderTest {

    @Test
    public void shouldBuildFriendshipDataSuccessfully() {
        FriendshipData data = FriendshipDataBuilder.builder()
                .withFrom("userFrom")
                .withPassword("12345678")
            .build();
        assertEquals("userFrom", data.from());
        assertEquals("12345678", data.password());
    }
}
