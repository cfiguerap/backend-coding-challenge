package es.adevinta.spain.friends.infra.friendship.common.repository;

import es.adevinta.spain.friends.domain.friendship.common.model.Friendship;
import es.adevinta.spain.friends.domain.user.model.vo.Username;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository {

    public boolean exists(Friendship friendship);

    public List<Friendship> friendships(Username username);

    public void save(Friendship friendship);

    public void remove(Friendship friendship);
}
