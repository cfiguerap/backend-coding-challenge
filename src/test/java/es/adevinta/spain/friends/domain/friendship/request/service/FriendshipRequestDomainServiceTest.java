package es.adevinta.spain.friends.domain.friendship.request.service;

import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequestBuilder;
import es.adevinta.spain.friends.domain.friendship.request.model.NotValidFriendshipRequestException;
import es.adevinta.spain.friends.domain.friendship.request.validation.FriendshipRequestValidator;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.infra.friendship.request.model.FriendshipRequestEntityConverter;
import es.adevinta.spain.friends.infra.friendship.request.repository.FriendshipRequestRepository;
import es.adevinta.spain.friends.infra.friendship.request.repository.FriendshipRequestRepositoryInMemory;
import es.adevinta.spain.friends.infra.user.UserUtils;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FriendshipRequestDomainServiceTest {

    private FriendshipRequestDomainService friendshipRequestDomainService;
    private FriendshipRequestValidator friendshipRequestValidator;
    private FriendshipRequestRepository friendshipRequestRepository;

    @Before
    public void setUp() {
        friendshipRequestRepository = new FriendshipRequestRepositoryInMemory(new FriendshipRequestEntityConverter(new UserEntityConverter()));
        friendshipRequestValidator = new FriendshipRequestValidator(friendshipRequestRepository);
        friendshipRequestDomainService = new FriendshipRequestDomainService(friendshipRequestValidator, friendshipRequestRepository);
    }

    @Test
    public void shouldReturnFriendshipRequestsSuccessfully() throws NotValidFriendshipRequestException {
        User userFrom = UserUtils.randomUser();
        User userTo = UserUtils.randomUser();
        assertTrue(friendshipRequestRepository.requestsFromUsername(userFrom.username()).isEmpty());

        FriendshipRequest request = FriendshipRequestBuilder.builder().withFrom(userFrom).withTo(userTo).build();
        friendshipRequestDomainService.requestFriendship(request);

        List<FriendshipRequest> friendshipRequests = friendshipRequestRepository.requestsFromUsername(userFrom.username());
        assertEquals(1, friendshipRequests.size());
        assertEquals(userFrom, friendshipRequests.get(0).from());
        assertEquals(userTo, friendshipRequests.get(0).to());
    }

    @Test(expected = NotValidFriendshipRequestException.class)
    public void shouldNotRegisterDuplicatedCrossedUsers() throws NotValidFriendshipRequestException {
        User userFrom = UserUtils.randomUser();
        User userTo = UserUtils.randomUser();
        assertTrue(friendshipRequestRepository.requestsFromUsername(userFrom.username()).isEmpty());

        FriendshipRequest request = FriendshipRequestBuilder.builder().withFrom(userFrom).withTo(userTo).build();
        friendshipRequestDomainService.requestFriendship(request);

        request = FriendshipRequestBuilder.builder().withFrom(userTo).withTo(userFrom).build();
        friendshipRequestDomainService.requestFriendship(request);
    }

    @Test(expected = NotValidFriendshipRequestException.class)
    public void shouldNotRegisterDuplicatedUsers() throws NotValidFriendshipRequestException {
        User userFrom = UserUtils.randomUser();
        User userTo = UserUtils.randomUser();
        assertTrue(friendshipRequestRepository.requestsFromUsername(userFrom.username()).isEmpty());

        FriendshipRequest request = FriendshipRequestBuilder.builder().withFrom(userFrom).withTo(userTo).build();
        friendshipRequestDomainService.requestFriendship(request);
        friendshipRequestDomainService.requestFriendship(request);
    }
}
