package es.adevinta.spain.friends.application.friendship.request;

import es.adevinta.spain.friends.application.friendship.request.model.FriendshipRequestData;
import es.adevinta.spain.friends.application.friendship.request.model.FriendshipRequestDataBuilder;
import es.adevinta.spain.friends.application.user.registration.model.NotValidRegisterException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FriendshipRequestDataBuilderTest {

    @Test
    public void shouldBuilderCreateUserDataSuccessfully() throws NotValidRegisterException {
        FriendshipRequestData data = FriendshipRequestDataBuilder.builder()
                .withFrom("userFrom")
                .withPassword("12345678")
                .withTo("userTo")
            .build();
        assertEquals("userFrom", data.from());
        assertEquals("12345678", data.password());
        assertEquals("userTo", data.to());
    }
}
