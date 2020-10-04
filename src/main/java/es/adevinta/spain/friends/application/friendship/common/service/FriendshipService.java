package es.adevinta.spain.friends.application.friendship.common.service;

import es.adevinta.spain.friends.application.friendship.common.validator.FriendshipDataValidator;
import es.adevinta.spain.friends.application.friendship.request.model.FriendshipData;
import es.adevinta.spain.friends.application.friendship.request.model.FriendshipRequestData;
import es.adevinta.spain.friends.application.user.registration.model.IncorrectPasswordException;
import es.adevinta.spain.friends.application.user.registration.model.UserNotFoundException;
import es.adevinta.spain.friends.domain.friendship.common.model.CannotAcceptFriendshipException;
import es.adevinta.spain.friends.domain.friendship.common.model.CannotDeclineFriendshipException;
import es.adevinta.spain.friends.domain.friendship.common.service.FriendshipDomainService;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequestBuilder;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.domain.user.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendshipService {

    private FriendshipDataValidator friendshipDataValidator;

    private FriendshipDomainService friendshipDomainService;

    private UserDomainService userDomainService;

    @Autowired
    public FriendshipService(
        FriendshipDataValidator friendshipDataValidator,
        FriendshipDomainService friendshipDomainService,
        UserDomainService userDomainService
    ) {
        this.friendshipDataValidator = friendshipDataValidator;
        this.friendshipDomainService = friendshipDomainService;
        this.userDomainService = userDomainService;
    }

    public void acceptFriendship(FriendshipRequestData friendshipRequestData) throws CannotAcceptFriendshipException, UserNotFoundException, IncorrectPasswordException {
        FriendshipRequest friendshipRequest = dtoToRequest(friendshipRequestData);
        friendshipDomainService.acceptFriendship(friendshipRequest);
    }

    public void declineFriendship(FriendshipRequestData friendshipRequestData) throws CannotDeclineFriendshipException, UserNotFoundException, IncorrectPasswordException {
        FriendshipRequest friendshipRequest = dtoToRequest(friendshipRequestData);
        friendshipDomainService.declineFriendship(friendshipRequest);
    }

    public List<String> friendships(FriendshipData friendshipData) throws UserNotFoundException, IncorrectPasswordException {
        User user = userDomainService.user(Username.create(friendshipData.from()));
        friendshipDataValidator.authorize(user, friendshipData.password());
        List<Username> friendUsernames = friendshipDomainService.friendships(user);
        return friendUsernames.stream().map(t -> t.value()).collect(Collectors.toList());
    }


    protected FriendshipRequest dtoToRequest(FriendshipRequestData friendshipRequestData) throws UserNotFoundException, IncorrectPasswordException {
        User userFrom = userDomainService.user(Username.create(friendshipRequestData.from()));
        friendshipDataValidator.authorize(userFrom, friendshipRequestData.password());
        User userTo = userDomainService.user(Username.create(friendshipRequestData.to()));
        return FriendshipRequestBuilder.builder()
                .withFrom(userFrom)
                .withTo(userTo)
            .build();
    }
}
