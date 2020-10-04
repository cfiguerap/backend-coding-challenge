package es.adevinta.spain.friends.infra.friendship.request.repository;

import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRequestRepository {

    boolean exists(FriendshipRequest friendshipRequest);

    List<FriendshipRequest> requestsFromUsername(Username username);

    void save(FriendshipRequest friendshipRequest);

    void remove(FriendshipRequest friendshipRequest);
}
