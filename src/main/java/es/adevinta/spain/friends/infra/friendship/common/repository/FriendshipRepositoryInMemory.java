package es.adevinta.spain.friends.infra.friendship.common.repository;

import es.adevinta.spain.friends.domain.friendship.common.model.Friendship;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import es.adevinta.spain.friends.infra.friendship.common.model.FriendshipEntity;
import es.adevinta.spain.friends.infra.friendship.common.model.FriendshipEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FriendshipRepositoryInMemory implements FriendshipRepository {

    private final Map<String, List<FriendshipEntity>> friendships = new ConcurrentHashMap<>();

    private FriendshipEntityConverter converter;

    @Autowired
    public FriendshipRepositoryInMemory(FriendshipEntityConverter converter) {
        this.converter = converter;
    }

    @Override
    public boolean exists(Friendship friendship) {
        List<Friendship> fromMe = this.friendships(friendship.from().username());
        for (Friendship relation : fromMe) {
            if (relation.to().equals(friendship.to())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Friendship> friendships(Username username) {
        List<FriendshipEntity> friends = this.friendships.get(username.value());
        if (friends == null) {
            friends = new ArrayList<>();
        }
        return converter.source(friends);
    }

    @Override
    public void save(Friendship friendship) {
        List<FriendshipEntity> friends = friendships.get(friendship.from().username().value());
        if (friends == null) {
            friends = new ArrayList<>();
        }
        friends.add(converter.target(friendship));
        friendships.put(friendship.from().username().value(), friends);
    }

    @Override
    public void remove(Friendship friendship) {
        List<FriendshipEntity> friends = friendships.get(friendship.from().username().value());
        if (friends == null) {
            return;
        }
        friends.remove(converter.target(friendship));
    }
}
