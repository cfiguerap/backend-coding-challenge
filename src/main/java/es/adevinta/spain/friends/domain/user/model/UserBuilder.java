package es.adevinta.spain.friends.domain.user.model;

import es.adevinta.spain.friends.domain.user.model.vo.Password;
import es.adevinta.spain.friends.domain.user.model.vo.Username;

public class UserBuilder {

    private Username username;
    private Password password;

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UserBuilder withUsername(Username username) {
        this.username = username;
        return this;
    }

    public UserBuilder withPassword(Password password) {
        this.password = password;
        return this;
    }

    public User build() {
        return new User(username, password);
    }
}