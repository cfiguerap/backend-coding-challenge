package es.adevinta.spain.friends.infra.friendship.common.model;

import es.adevinta.spain.friends.infra.user.model.UserEntity;

import java.util.Objects;

public class FriendshipEntity {

    private final UserEntity from;
    private final UserEntity to;

    public FriendshipEntity(UserEntity from, UserEntity to) {
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
        FriendshipEntity entity = (FriendshipEntity) o;
        return Objects.equals(from, entity.from) &&
                Objects.equals(to, entity.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
