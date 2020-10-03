package es.adevinta.spain.friends.domain.user.model;

import es.adevinta.spain.friends.domain.common.AbstractDomainObject;
import es.adevinta.spain.friends.domain.user.model.vo.Password;
import es.adevinta.spain.friends.domain.user.model.vo.Username;

public class User extends AbstractDomainObject<UserId> {

    private Username username;
    private Password password;

    public User(Username username, Password password) {
        super(UserId.create(username, password));
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