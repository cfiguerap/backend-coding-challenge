package es.adevinta.spain.friends.domain.friendship.common.service;

import es.adevinta.spain.friends.domain.friendship.common.model.CannotAcceptFriendshipException;
import es.adevinta.spain.friends.domain.friendship.common.model.CannotDeclineFriendshipException;
import es.adevinta.spain.friends.domain.friendship.common.model.Friendship;
import es.adevinta.spain.friends.domain.friendship.common.model.FriendshipBuilder;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequestBuilder;
import es.adevinta.spain.friends.domain.friendship.request.validation.FriendshipRequestValidator;
import es.adevinta.spain.friends.domain.user.UserTestUtils;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.infra.friendship.common.model.FriendshipEntityConverter;
import es.adevinta.spain.friends.infra.friendship.common.repository.FriendshipRepository;
import es.adevinta.spain.friends.infra.friendship.common.repository.FriendshipRepositoryInMemory;
import es.adevinta.spain.friends.infra.friendship.request.model.FriendshipRequestEntityConverter;
import es.adevinta.spain.friends.infra.friendship.request.repository.FriendshipRequestRepository;
import es.adevinta.spain.friends.infra.friendship.request.repository.FriendshipRequestRepositoryInMemory;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FriendshipDomainServiceTest {

    private FriendshipDomainService friendshipDomainService;
    private FriendshipRequestValidator friendshipRequestValidator;
    private FriendshipRequestRepository friendshipRequestRepository;
    private FriendshipRepository friendshipRepository;

    @Before
    public void setUp() {
        friendshipRepository = new FriendshipRepositoryInMemory(new FriendshipEntityConverter(new UserEntityConverter()));
        friendshipRequestRepository = new FriendshipRequestRepositoryInMemory(new FriendshipRequestEntityConverter(new FriendshipEntityConverter(new UserEntityConverter())));
        friendshipRequestValidator = new FriendshipRequestValidator(friendshipRepository, friendshipRequestRepository);
        friendshipDomainService = new FriendshipDomainService(friendshipRequestValidator, friendshipRepository, friendshipRequestRepository);
    }

    @Test
    public void shouldAcceptFriendshipRequestSuccessfully() throws CannotAcceptFriendshipException {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();

        FriendshipRequest request = FriendshipRequestBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
            .build();
        friendshipRequestRepository.save(request);
        friendshipDomainService.acceptFriendship(request.reverse());
    }

    @Test(expected = CannotAcceptFriendshipException.class)
    public void shouldNotAcceptFriendshipRequestIfTheyAreFriends() throws CannotAcceptFriendshipException {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();

        FriendshipRequest request = FriendshipRequestBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
            .build();
        Friendship friendship = FriendshipBuilder.builder()
                .withFriendshipRequest(request)
            .build();
        friendshipRepository.save(friendship);
        friendshipDomainService.acceptFriendship(request.reverse());
    }

    @Test(expected = CannotAcceptFriendshipException.class)
    public void shouldNotAcceptFriendshipRequestIfNotExists() throws CannotAcceptFriendshipException {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();

        FriendshipRequest request = FriendshipRequestBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
            .build();
        friendshipRequestRepository.remove(request);
        friendshipDomainService.acceptFriendship(request.reverse());
    }

    @Test
    public void shouldDeclineFriendshipRequestSuccessfully() throws CannotDeclineFriendshipException {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();

        FriendshipRequest request = FriendshipRequestBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
            .build();
        friendshipRequestRepository.save(request);
        friendshipDomainService.declineFriendship(request.reverse());
    }

    @Test(expected = CannotDeclineFriendshipException.class)
    public void shouldNotDeclineFriendshipRequestIfNotExists() throws CannotDeclineFriendshipException {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();

        FriendshipRequest request = FriendshipRequestBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
            .build();
        friendshipRequestRepository.remove(request);
        friendshipDomainService.declineFriendship(request.reverse());
    }

    @Test
    public void shouldShowFriendsSuccessfully() throws CannotAcceptFriendshipException {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();

        FriendshipRequest request = FriendshipRequestBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
                .build();
        friendshipRequestRepository.save(request);

        assertTrue(friendshipDomainService.friendships(userFrom).isEmpty());
        assertTrue(friendshipDomainService.friendships(userTo).isEmpty());

        friendshipDomainService.acceptFriendship(request.reverse());

        assertFalse(friendshipDomainService.friendships(userFrom).isEmpty());
        assertFalse(friendshipDomainService.friendships(userTo).isEmpty());

        assertEquals(userTo.username().value(), friendshipDomainService.friendships(userFrom).get(0).value());
        assertEquals(userFrom.username().value(), friendshipDomainService.friendships(userTo).get(0).value());
    }
}
