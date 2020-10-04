package es.adevinta.spain.friends.domain.friendship.request.validation;

import es.adevinta.spain.friends.domain.friendship.common.model.FriendshipBuilder;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.NotValidFriendshipRequestException;
import es.adevinta.spain.friends.infra.friendship.common.repository.FriendshipRepository;
import es.adevinta.spain.friends.infra.friendship.request.repository.FriendshipRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FriendshipRequestValidator {

    private FriendshipRepository friendshipRepository;

    private FriendshipRequestRepository friendshipRequestRepository;

    @Autowired
    public FriendshipRequestValidator(
        FriendshipRepository friendshipRepository,
        FriendshipRequestRepository friendshipRequestRepository
    ) {
        this.friendshipRepository = friendshipRepository;
        this.friendshipRequestRepository = friendshipRequestRepository;
    }

    public void checkValid(FriendshipRequest friendshipRequest) throws NotValidFriendshipRequestException {
        if (areFromAndToEquals(friendshipRequest) || isRequestDuplicated(friendshipRequest) || areFriendsNow(friendshipRequest)) {
            throw new NotValidFriendshipRequestException("Not valid friendship request");
        }
    }

    private boolean areFriendsNow(FriendshipRequest friendshipRequest) {
        return friendshipRepository.exists(FriendshipBuilder.builder().withFriendshipRequest(friendshipRequest).build());
    }

    private boolean areFromAndToEquals(FriendshipRequest friendshipRequest) {
        return friendshipRequest.from().equals(friendshipRequest.to());
    }

    private boolean isRequestDuplicated(FriendshipRequest friendshipRequest) {
        return friendshipRequestRepository.exists(friendshipRequest)
                || friendshipRequestRepository.exists(friendshipRequest.reverse());
    }
}
