package es.adevinta.spain.friends.infra.friendship.request.repository;

import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRequestRepository {

    public boolean exists(FriendshipRequest friendshipRequest);

    public List<FriendshipRequest> requestsFromUsername(Username username);

    public void save(FriendshipRequest friendshipRequest);

    public void remove(FriendshipRequest friendshipRequest);
}
