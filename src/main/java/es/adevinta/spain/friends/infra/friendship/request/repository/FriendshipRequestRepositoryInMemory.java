package es.adevinta.spain.friends.infra.friendship.request.repository;

import es.adevinta.spain.friends.domain.friendship.request.model.FriendshipRequest;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.infra.friendship.request.model.FriendshipRequestEntity;
import es.adevinta.spain.friends.infra.friendship.request.model.FriendshipRequestEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FriendshipRequestRepositoryInMemory implements FriendshipRequestRepository {

    private final Map<String, List<FriendshipRequestEntity>> requests = new ConcurrentHashMap<>();

    private FriendshipRequestEntityConverter converter;

    @Autowired
    public FriendshipRequestRepositoryInMemory(FriendshipRequestEntityConverter converter) {
        this.converter = converter;
    }

    @Override
    public List<FriendshipRequest> requestsFromUsername(Username username) {
        List<FriendshipRequestEntity> userRequests = requests.get(username.value());
        if (userRequests == null) {
            userRequests = new ArrayList<>();
        }
        return converter.source(userRequests);
    }

    @Override
    public void save(FriendshipRequest friendshipRequest) {
        List<FriendshipRequestEntity> userRequests = requests.get(friendshipRequest.from().username().value());
        if (userRequests == null) {
            userRequests = new ArrayList<>();
        }
        userRequests.add(converter.target(friendshipRequest));
        requests.put(friendshipRequest.from().username().value(), userRequests);
    }

    @Override
    public void remove(FriendshipRequest friendshipRequest) {
        List<FriendshipRequestEntity> userRequests = requests.get(friendshipRequest.from().username().value());
        if (userRequests == null) {
            return;
        }
        userRequests.remove(converter.target(friendshipRequest));
    }
}
