package es.adevinta.spain.friends.domain.user.model;

import es.adevinta.spain.friends.domain.common.AbstractDomainObject;
import es.adevinta.spain.friends.domain.user.model.vo.Password;
import es.adevinta.spain.friends.domain.user.model.vo.Username;

import java.io.Serializable;
import java.util.Objects;

public class User extends AbstractDomainObject<UserId> {

    private Username username;
    private Password password;

    public User(Username username, Password password) {
        super(UserId.create(username.value(), password.value()));
        this.username = username;
        this.password = password;
    }

    public Username username() {
        return username;
    }

    public Password password() {
        return password;
    }
}