package es.adevinta.spain.friends.infra.friendship.common.repository;

import es.adevinta.spain.friends.domain.friendship.common.model.Friendship;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository {

    boolean exists(Friendship friendship);

    List<Friendship> friendships(Username username);

    void save(Friendship friendship);

    void remove(Friendship friendship);
}
