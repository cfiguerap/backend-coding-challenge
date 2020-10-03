package es.adevinta.spain.friends.application.friendship.request.service;

import es.adevinta.spain.friends.application.friendship.request.model.FriendshipRequestData;
import es.adevinta.spain.friends.application.user.registration.model.IncorrectPasswordException;
import es.adevinta.spain.friends.application.user.registration.model.UserNotFoundException;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequestBuilder;
import es.adevinta.spain.friends.domain.friendship.request.model.NotValidFriendshipRequestException;
import es.adevinta.spain.friends.domain.friendship.request.service.FriendshipRequestDomainService;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.vo.Password;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.domain.user.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipRequestService {

    private FriendshipRequestDomainService friendshipRequestDomainService;

    private UserDomainService userDomainService;

    @Autowired
    public FriendshipRequestService(
            FriendshipRequestDomainService friendshipRequestDomainService,
            UserDomainService userDomainService
    ) {
        this.friendshipRequestDomainService = friendshipRequestDomainService;
        this.userDomainService = userDomainService;
    }


    public void request(FriendshipRequestData friendshipRequestData) throws NotValidFriendshipRequestException, UserNotFoundException, IncorrectPasswordException {
        User from = userDomainService.user(Username.create(friendshipRequestData.from()));
        from.validate(Password.create(friendshipRequestData.password()));
        User to = userDomainService.user(Username.create(friendshipRequestData.to()));
        FriendshipRequest friendshipRequest = FriendshipRequestBuilder.builder()
                .withFrom(from)
                .withTo(to)
            .build();
        friendshipRequestDomainService.requestFriendship(friendshipRequest);
    }
}
