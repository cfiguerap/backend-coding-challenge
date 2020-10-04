package es.adevinta.spain.friends.domain.user.model;

import es.adevinta.spain.friends.application.user.registration.model.IncorrectPasswordException;
import es.adevinta.spain.friends.domain.common.AbstractDomainObject;
import es.adevinta.spain.friends.domain.user.model.vo.Password;
import es.adevinta.spain.friends.domain.user.model.vo.Username;

public class User extends AbstractDomainObject<UserId> {

    private Password password;

    public User(Username username, Password password) {
        super(UserId.create(username));
        this.password = password;
    }

    public Username username() {
        return Username.create(id().toString());
    }

    public Password password() {
        return password;
    }

    public void validate(Password password) throws IncorrectPasswordException {
        if (!this.password.equals(password)) {
            throw new IncorrectPasswordException("Incorrect password");
        }
    }
}