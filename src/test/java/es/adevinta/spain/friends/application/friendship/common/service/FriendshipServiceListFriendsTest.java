package es.adevinta.spain.friends.application.friendship.common.service;

import es.adevinta.spain.friends.application.friendship.common.validator.FriendshipDataValidator;
import es.adevinta.spain.friends.application.friendship.request.model.FriendshipDataBuilder;
import es.adevinta.spain.friends.application.friendship.request.model.FriendshipRequestDataBuilder;
import es.adevinta.spain.friends.application.friendship.request.service.FriendshipRequestService;
import es.adevinta.spain.friends.application.user.registration.model.IncorrectPasswordException;
import es.adevinta.spain.friends.application.user.registration.model.NotValidRegisterException;
import es.adevinta.spain.friends.application.user.registration.model.UserNotFoundException;
import es.adevinta.spain.friends.application.user.registration.service.UserService;
import es.adevinta.spain.friends.domain.friendship.common.model.CannotAcceptFriendshipException;
import es.adevinta.spain.friends.domain.friendship.common.model.FriendshipBuilder;
import es.adevinta.spain.friends.domain.friendship.common.service.FriendshipDomainService;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequestBuilder;
import es.adevinta.spain.friends.domain.friendship.request.model.NotValidFriendshipRequestException;
import es.adevinta.spain.friends.domain.friendship.request.service.FriendshipRequestDomainService;
import es.adevinta.spain.friends.domain.friendship.request.validation.FriendshipRequestValidator;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.UserBuilder;
import es.adevinta.spain.friends.domain.user.model.vo.Password;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.domain.user.service.UserDomainService;
import es.adevinta.spain.friends.infra.friendship.common.model.FriendshipEntityConverter;
import es.adevinta.spain.friends.infra.friendship.common.repository.FriendshipRepository;
import es.adevinta.spain.friends.infra.friendship.common.repository.FriendshipRepositoryInMemory;
import es.adevinta.spain.friends.infra.friendship.request.model.FriendshipRequestEntityConverter;
import es.adevinta.spain.friends.infra.friendship.request.repository.FriendshipRequestRepository;
import es.adevinta.spain.friends.infra.friendship.request.repository.FriendshipRequestRepositoryInMemory;
import es.adevinta.spain.friends.infra.user.model.UserEntityConverter;
import es.adevinta.spain.friends.infra.user.repository.UserRepository;
import es.adevinta.spain.friends.infra.user.repository.UserRepositoryInMemory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FriendshipServiceListFriendsTest {

    private FriendshipService friendshipService;
    private FriendshipRepository friendshipRepository;

    private FriendshipRequestService friendshipRequestService;
    private FriendshipRequestRepository friendshipRequestRepository;
    private UserDomainService userDomainService;

    @Before
    public void setUp() {
        UserRepository userRepository = new UserRepositoryInMemory(new UserEntityConverter());
        userDomainService = new UserDomainService(userRepository);
        UserService userService = new UserService(userDomainService);

        friendshipRepository = new FriendshipRepositoryInMemory(new FriendshipEntityConverter(new UserEntityConverter()));
        friendshipRequestRepository = new FriendshipRequestRepositoryInMemory(new FriendshipRequestEntityConverter(new FriendshipEntityConverter(new UserEntityConverter())));

        FriendshipRequestValidator friendshipRequestValidator = new FriendshipRequestValidator(friendshipRepository, friendshipRequestRepository);
        FriendshipRequestDomainService friendshipRequestDomainService = new FriendshipRequestDomainService(friendshipRequestValidator, friendshipRequestRepository);
        FriendshipDataValidator friendshipDataValidator = new FriendshipDataValidator();
        FriendshipDomainService friendshipDomainService = new FriendshipDomainService(friendshipRepository, friendshipRequestRepository);
        friendshipRequestService = new FriendshipRequestService(
            friendshipDataValidator,
            friendshipRequestDomainService,
            friendshipDomainService,
            userDomainService
        );
        friendshipService = new FriendshipService(
            friendshipDataValidator,
            friendshipDomainService,
            userDomainService
        );
    }

    @Test
    public void shouldAcceptFriendshipSuccessfully() throws NotValidRegisterException, UserNotFoundException, IncorrectPasswordException, NotValidFriendshipRequestException, CannotAcceptFriendshipException {
        User userFrom = UserBuilder.builder().withUsername(Username.create("username1")).withPassword(Password.create("password123")).build();
        User userTo = UserBuilder.builder().withUsername(Username.create("username2")).withPassword(Password.create("password456")).build();
        assertFalse(userDomainService.exists(userFrom));
        assertFalse(userDomainService.exists(userTo));

        userDomainService.register(userFrom);
        userDomainService.register(userTo);
        assertTrue(userDomainService.exists(userFrom));
        assertTrue(userDomainService.exists(userTo));

        assertFalse(friendshipRequestRepository.exists(FriendshipRequestBuilder.builder()
                    .withFrom(userFrom).withTo(userTo).build()));
        friendshipRequestService.requestFriendship(
            FriendshipRequestDataBuilder.builder()
                    .withFrom(userFrom.username().value())
                    .withPassword(userFrom.password().value())
                    .withTo(userTo.username().value())
                .build()
        );
        assertTrue(friendshipRequestRepository.exists(FriendshipRequestBuilder.builder()
                .withFrom(userFrom).withTo(userTo).build()));

        assertFalse(friendshipRepository.exists(FriendshipBuilder.builder()
                .withFrom(userFrom).withTo(userTo).build()));

        assertTrue(friendshipService.friendships(FriendshipDataBuilder.builder()
                .withFrom("username1").withPassword("password123").build()).isEmpty());
        assertTrue(friendshipService.friendships(FriendshipDataBuilder.builder()
                .withFrom("username2").withPassword("password456").build()).isEmpty());

        friendshipService.acceptFriendship(
            FriendshipRequestDataBuilder.builder()
                    .withFrom(userTo.username().value())
                    .withPassword(userTo.password().value())
                    .withTo(userFrom.username().value())
                .build()
        );
        assertTrue(friendshipRepository.exists(FriendshipBuilder.builder()
                .withFrom(userFrom).withTo(userTo).build()));
        assertFalse(friendshipRequestRepository.exists(FriendshipRequestBuilder.builder()
                .withFrom(userFrom).withTo(userTo).build()));

        assertFalse(friendshipService.friendships(FriendshipDataBuilder.builder()
                .withFrom("username1").withPassword("password123").build()).isEmpty());
        assertFalse(friendshipService.friendships(FriendshipDataBuilder.builder()
                .withFrom("username2").withPassword("password456").build()).isEmpty());
    }

    @Test(expected = IncorrectPasswordException.class)
    public void shouldNotAcceptFriendshipIncorrectPasword() throws NotValidRegisterException, UserNotFoundException, IncorrectPasswordException, NotValidFriendshipRequestException, CannotAcceptFriendshipException {
        User userFrom = UserBuilder.builder().withUsername(Username.create("username1")).withPassword(Password.create("password123")).build();
        User userTo = UserBuilder.builder().withUsername(Username.create("username2")).withPassword(Password.create("password456")).build();

        userDomainService.register(userFrom);
        userDomainService.register(userTo);

        friendshipRequestService.requestFriendship(
            FriendshipRequestDataBuilder.builder()
                    .withFrom(userFrom.username().value())
                    .withPassword(userFrom.password().value())
                    .withTo(userTo.username().value())
                .build()
        );

        assertTrue(friendshipService.friendships(FriendshipDataBuilder.builder()
                .withFrom("username1").withPassword("password123").build()).isEmpty());
        assertTrue(friendshipService.friendships(FriendshipDataBuilder.builder()
                .withFrom("username2").withPassword("password456").build()).isEmpty());

        friendshipService.acceptFriendship(
            FriendshipRequestDataBuilder.builder()
                    .withFrom(userTo.username().value())
                    .withPassword("ThisPasswordIsIncorrect")
                    .withTo(userFrom.username().value())
                .build()
        );

        assertTrue(friendshipService.friendships(FriendshipDataBuilder.builder()
                .withFrom("username1").withPassword("password123").build()).isEmpty());
        assertTrue(friendshipService.friendships(FriendshipDataBuilder.builder()
                .withFrom("username2").withPassword("password456").build()).isEmpty());
    }


}
