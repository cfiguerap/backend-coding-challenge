package es.adevinta.spain.friends.domain.user.model;

import es.adevinta.spain.friends.domain.user.UserTestUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserBuilderTest {

    @Test
    public void shouldBuilderCreateUsersSuccessfully() {
        User user1 = UserTestUtils.randomUser();
        User user2 = UserBuilder.builder()
                .withUsername(user1.username())
                .withPassword(user1.password())
            .build();
        assertEquals(user1, user2);
    }
}
