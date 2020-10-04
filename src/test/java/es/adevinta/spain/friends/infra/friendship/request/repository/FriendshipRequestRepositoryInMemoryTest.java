package es.adevinta.spain.friends.infra.friendship.request.repository;

import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequestBuilder;
import es.adevinta.spain.friends.domain.user.UserTestUtils;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.infra.friendship.common.model.FriendshipEntityConverter;
import es.adevinta.spain.friends.infra.friendship.request.model.FriendshipRequestEntityConverter;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FriendshipRequestRepositoryInMemoryTest {

    private FriendshipRequestRepositoryInMemory repository;

    @Before
    public void setUp() {
        repository = new FriendshipRequestRepositoryInMemory(
                new FriendshipRequestEntityConverter(new FriendshipEntityConverter(new UserEntityConverter())));
    }

    @Test
    public void shouldNotFindAnyFriendshipRequest() {
        assertTrue(repository.requestsFromUsername(Username.create("not_exists")).isEmpty());
    }

    @Test
    public void shouldFindAndSaveFriendshipRequestsSuccessfully() {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();
        FriendshipRequest friendshipRequest = FriendshipRequestBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
            .build();

        assertFalse(repository.exists(friendshipRequest));
        assertFalse(repository.exists(friendshipRequest.reverse()));
        assertTrue(repository.requestsFromUsername(userFrom.username()).isEmpty());
        repository.save(friendshipRequest);

        assertFalse(repository.exists(friendshipRequest.reverse()));
        assertTrue(repository.exists(friendshipRequest));
        List<FriendshipRequest> requests = repository.requestsFromUsername(userFrom.username());
        assertFalse(requests.isEmpty());
        assertEquals(userFrom, requests.get(0).from());
        assertEquals(userTo, requests.get(0).to());
    }

    @Test
    public void shouldRemoveFriendshipRequestsSuccessfully() {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();
        FriendshipRequest friendshipRequest = FriendshipRequestBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
                .build();

        assertFalse(repository.exists(friendshipRequest));
        assertFalse(repository.exists(friendshipRequest.reverse()));
        assertTrue(repository.requestsFromUsername(userFrom.username()).isEmpty());
        repository.save(friendshipRequest);

        assertFalse(repository.exists(friendshipRequest.reverse()));
        assertTrue(repository.exists(friendshipRequest));

        assertFalse(repository.requestsFromUsername(userFrom.username()).isEmpty());
        repository.remove(friendshipRequest);

        assertFalse(repository.exists(friendshipRequest.reverse()));
        assertFalse(repository.exists(friendshipRequest));
        assertTrue(repository.requestsFromUsername(userFrom.username()).isEmpty());
    }
}
