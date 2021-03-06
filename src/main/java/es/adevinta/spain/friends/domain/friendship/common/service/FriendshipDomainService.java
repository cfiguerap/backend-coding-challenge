package es.adevinta.spain.friends.domain.friendship.common.service;

import es.adevinta.spain.friends.domain.friendship.common.model.CannotAcceptFriendshipException;
import es.adevinta.spain.friends.domain.friendship.common.model.CannotDeclineFriendshipException;
import es.adevinta.spain.friends.domain.friendship.common.model.Friendship;
import es.adevinta.spain.friends.domain.friendship.common.model.FriendshipBuilder;
import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.user.model.User;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.infra.friendship.common.repository.FriendshipRepository;
import es.adevinta.spain.friends.infra.friendship.request.repository.FriendshipRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendshipDomainService {

    private final FriendshipRepository friendshipRepository;

    private final FriendshipRequestRepository friendshipRequestRepository;

    @Autowired
    public FriendshipDomainService(
            FriendshipRepository friendshipRepository,
            FriendshipRequestRepository friendshipRequestRepository
        ) {
        this.friendshipRepository = friendshipRepository;
        this.friendshipRequestRepository = friendshipRequestRepository;
    }

    public List<Username> friendships(User user) {
        List<Friendship> friends = friendshipRepository.friendships(user.username());
        return friends.stream().map(t -> t.to().username()).collect(Collectors.toList());
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
