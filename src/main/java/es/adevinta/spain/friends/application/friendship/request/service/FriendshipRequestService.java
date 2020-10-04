package es.adevinta.spain.friends.application.friendship.request.service;

import es.adevinta.spain.friends.application.friendship.common.service.FriendshipService;
import es.adevinta.spain.friends.application.friendship.common.validator.FriendshipDataValidator;
import es.adevinta.spain.friends.application.friendship.request.model.FriendshipRequestData;
import es.adevinta.spain.friends.application.user.registration.model.IncorrectPasswordException;
import es.adevinta.spain.friends.application.user.registration.model.UserNotFoundException;
import es.adevinta.spain.friends.domain.friendship.common.service.FriendshipDomainService;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.NotValidFriendshipRequestException;
import es.adevinta.spain.friends.domain.friendship.request.service.FriendshipRequestDomainService;
import es.adevinta.spain.friends.domain.user.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipRequestService extends FriendshipService {

    private FriendshipRequestDomainService friendshipRequestDomainService;

    @Autowired
    public FriendshipRequestService(
            FriendshipDataValidator friendshipDataValidator,
            FriendshipRequestDomainService friendshipRequestDomainService,
            FriendshipDomainService friendshipDomainService,
            UserDomainService userDomainService
    ) {
        super(friendshipDataValidator, friendshipDomainService, userDomainService);
        this.friendshipRequestDomainService = friendshipRequestDomainService;
    }

    public void requestFriendship(FriendshipRequestData friendshipRequestData) throws NotValidFriendshipRequestException, UserNotFoundException, IncorrectPasswordException {
        FriendshipRequest friendshipRequest = dtoToRequest(friendshipRequestData);
        friendshipRequestDomainService.requestFriendship(friendshipRequest);
    }
}
