package es.adevinta.spain.friends.domain.friendship.common.service;

import es.adevinta.spain.friends.domain.friendship.common.model.CannotAcceptFriendshipException;
import es.adevinta.spain.friends.domain.friendship.common.model.CannotDeclineFriendshipException;
import es.adevinta.spain.friends.domain.friendship.common.model.Friendship;
import es.adevinta.spain.friends.domain.friendship.common.model.FriendshipBuilder;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.validation.FriendshipRequestValidator;
import es.adevinta.spain.friends.infra.friendship.common.repository.FriendshipRepository;
import es.adevinta.spain.friends.infra.friendship.request.repository.FriendshipRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipDomainService {

    private FriendshipRequestValidator friendshipRequestValidator;

    private FriendshipRepository friendshipRepository;

    private FriendshipRequestRepository friendshipRequestRepository;

    @Autowired
    public FriendshipDomainService(
            FriendshipRequestValidator friendshipRequestValidator,
            FriendshipRepository friendshipRepository,
            FriendshipRequestRepository friendshipRequestRepository
        ) {
        this.friendshipRequestValidator = friendshipRequestValidator;
        this.friendshipRepository = friendshipRepository;
        this.friendshipRequestRepository = friendshipRequestRepository;
    }

    public void acceptFriendship(FriendshipRequest friendshipRequest) throws CannotAcceptFriendshipException {
        Friendship newFriendship = FriendshipBuilder.builder()
                .withFriendshipRequest(friendshipRequest)
            .build();

        if (!friendshipRequestRepository.exists(friendshipRequest.reverse())) {
            throw new CannotAcceptFriendshipException("Friendship request not found");
        }
        if (friendshipRepository.exists(newFriendship)) {
            throw new CannotAcceptFriendshipException("Friendship exists now");
        }

        // delete request
        friendshipRequestRepository.remove(friendshipRequest.reverse());

        friendshipRepository.save(newFriendship);
        friendshipRepository.save(newFriendship.reverse());
    }

    public void declineFriendship(FriendshipRequest friendshipRequest) throws CannotDeclineFriendshipException {
        if (!friendshipRequestRepository.exists(friendshipRequest.reverse())) {
            throw new CannotDeclineFriendshipException("Friendship request not found");
        }
        // delete requests
        friendshipRequestRepository.remove(friendshipRequest);
        friendshipRequestRepository.remove(friendshipRequest.reverse());
    }
}
