package es.adevinta.spain.friends.infra.user.model;

import java.util.Objects;

public class UserEntity {

    private final String username;
    private final String password;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity entity = (UserEntity) o;
        return Objects.equals(username, entity.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
