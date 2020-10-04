package es.adevinta.spain.friends.domain.friendship.request.service;

import es.adevinta.spain.friends.UserTestUtils;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequestBuilder;
import es.adevinta.spain.friends.domain.friendship.request.model.NotValidFriendshipRequestException;
import es.adevinta.spain.friends.domain.friendship.request.validation.FriendshipRequestValidator;
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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FriendshipRequestDomainServiceTest {

    private FriendshipRequestDomainService friendshipRequestDomainService;
    private FriendshipRequestRepository friendshipRequestRepository;

    @Before
    public void setUp() {
        FriendshipRepository friendshipRepository = new FriendshipRepositoryInMemory(new FriendshipEntityConverter(new UserEntityConverter()));
        friendshipRequestRepository = new FriendshipRequestRepositoryInMemory(new FriendshipRequestEntityConverter(new FriendshipEntityConverter(new UserEntityConverter())));
        FriendshipRequestValidator friendshipRequestValidator = new FriendshipRequestValidator(friendshipRepository, friendshipRequestRepository);
        friendshipRequestDomainService = new FriendshipRequestDomainService(friendshipRequestValidator, friendshipRequestRepository);
    }

    @Test
    public void shouldReturnFriendshipRequestsSuccessfully() throws NotValidFriendshipRequestException {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();
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
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();
        assertTrue(friendshipRequestRepository.requestsFromUsername(userFrom.username()).isEmpty());

        FriendshipRequest request = FriendshipRequestBuilder.builder().withFrom(userFrom).withTo(userTo).build();
        friendshipRequestDomainService.requestFriendship(request);

        request = FriendshipRequestBuilder.builder().withFrom(userTo).withTo(userFrom).build();
        friendshipRequestDomainService.requestFriendship(request);
    }

    @Test(expected = NotValidFriendshipRequestException.class)
    public void shouldNotRegisterDuplicatedUsers() throws NotValidFriendshipRequestException {
        User userFrom = UserTestUtils.randomUser();
        User userTo = UserTestUtils.randomUser();
        assertTrue(friendshipRequestRepository.requestsFromUsername(userFrom.username()).isEmpty());

        FriendshipRequest request = FriendshipRequestBuilder.builder().withFrom(userFrom).withTo(userTo).build();
        friendshipRequestDomainService.requestFriendship(request);
        friendshipRequestDomainService.requestFriendship(request);
    }
}
