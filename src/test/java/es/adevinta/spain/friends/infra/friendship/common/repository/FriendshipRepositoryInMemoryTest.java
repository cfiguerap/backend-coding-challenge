package es.adevinta.spain.friends.infra.friendship.common.repository;

import es.adevinta.spain.friends.UserTestUtils;
import es.adevinta.spain.friends.domain.friendship.common.model.Friendship;
import es.adevinta.spain.friends.domain.friendship.common.model.FriendshipBuilder;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.infra.friendship.common.model.FriendshipEntityConverter;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FriendshipRepositoryInMemoryTest {

    private FriendshipRepositoryInMemory repository;

    @Before
    public void setUp() {
        repository = new FriendshipRepositoryInMemory(new FriendshipEntityConverter(new UserEntityConverter()));
    }

    @Test
    public void shouldNotFindAnyFriendship() {
        assertTrue(repository.friendships(Username.create("not_exists")).isEmpty());
    }

    @Test
    public void shouldFindAndSaveFriendshipsSuccessfully() {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();
        Friendship friendship = FriendshipBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
            .build();

        assertFalse(repository.exists(friendship));
        assertFalse(repository.exists(friendship.reverse()));
        assertTrue(repository.friendships(userFrom.username()).isEmpty());
        repository.save(friendship);

        assertTrue(repository.exists(friendship));
        List<Friendship> requests = repository.friendships(userFrom.username());
        assertFalse(requests.isEmpty());
        assertEquals(userFrom, requests.get(0).from());
        assertEquals(userTo, requests.get(0).to());
    }

    @Test
    public void shouldRemoveFriendshipsSuccessfully() {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();
        Friendship friendship = FriendshipBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
                .build();

        assertFalse(repository.exists(friendship));
        assertTrue(repository.friendships(userFrom.username()).isEmpty());
        repository.save(friendship);

        assertTrue(repository.exists(friendship));
        assertFalse(repository.friendships(userFrom.username()).isEmpty());
        repository.remove(friendship);

        assertFalse(repository.exists(friendship));
        assertTrue(repository.friendships(userFrom.username()).isEmpty());
    }
}
