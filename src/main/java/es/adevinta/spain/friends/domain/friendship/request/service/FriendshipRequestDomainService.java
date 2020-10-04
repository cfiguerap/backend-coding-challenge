package es.adevinta.spain.friends.domain.friendship.request.service;

import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.NotValidFriendshipRequestException;
import es.adevinta.spain.friends.domain.friendship.request.validation.FriendshipRequestValidator;
import es.adevinta.spain.friends.infra.friendship.request.repository.FriendshipRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipRequestDomainService {

    private final FriendshipRequestValidator friendshipRequestValidator;

    private final FriendshipRequestRepository friendshipRequestRepository;

    @Autowired
    public FriendshipRequestDomainService(
            FriendshipRequestValidator friendshipRequestValidator,
            FriendshipRequestRepository friendshipRequestRepository
    ) {
        this.friendshipRequestValidator = friendshipRequestValidator;
        this.friendshipRequestRepository = friendshipRequestRepository;
    }

    public void requestFriendship(FriendshipRequest friendshipRequest) throws NotValidFriendshipRequestException {
        friendshipRequestValidator.checkValid(friendshipRequest);
        friendshipRequestRepository.save(friendshipRequest);
    }
}
