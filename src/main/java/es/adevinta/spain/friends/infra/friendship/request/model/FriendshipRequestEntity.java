package es.adevinta.spain.friends.infra.friendship.request.model;

import es.adevinta.spain.friends.infra.user.model.UserEntity;

import java.util.Objects;

public class FriendshipRequestEntity {

    private UserEntity from;
    private UserEntity to;

    public FriendshipRequestEntity(UserEntity from, UserEntity to) {
        this.from = from;
        this.to = to;
    }

    public UserEntity from() {
        return from;
    }

    public UserEntity to() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipRequestEntity entity = (FriendshipRequestEntity) o;
        return Objects.equals(from, entity.from) &&
                Objects.equals(to, entity.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
