package es.adevinta.spain.friends.domain.friendship.request.validation;

import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.friendship.request.model.NotValidFriendshipRequestException;
import es.adevinta.spain.friends.infra.friendship.request.repository.FriendshipRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendshipRequestValidator {

    private FriendshipRequestRepository friendshipRequestRepository;

    @Autowired
    public FriendshipRequestValidator(FriendshipRequestRepository friendshipRequestRepository) {
        this.friendshipRequestRepository = friendshipRequestRepository;
    }

    public void checkValid(FriendshipRequest friendshipRequest) throws NotValidFriendshipRequestException {
        if (isRequestFromToEquals(friendshipRequest) || isRequestDuplicated(friendshipRequest)) {
            throw new NotValidFriendshipRequestException("Not valid friendship request");
        }
    }

    private boolean isRequestFromToEquals(FriendshipRequest friendshipRequest) {
        return friendshipRequest.from().equals(friendshipRequest.to());
    }

    private boolean isRequestDuplicated(FriendshipRequest friendshipRequest) {
        List<FriendshipRequest> fromMe = friendshipRequestRepository.requestsFromUsername(friendshipRequest.from().username());
        for (FriendshipRequest req : fromMe) {
            if (req.to().equals(friendshipRequest.to())) {
                return true;
            }
        }
        List<FriendshipRequest> toMe = friendshipRequestRepository.requestsFromUsername(friendshipRequest.to().username());
        for (FriendshipRequest req : toMe) {
            if (req.to().equals(friendshipRequest.from())) {
                return true;
            }
        }
        return false;
    }
}
