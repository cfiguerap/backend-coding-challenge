package es.adevinta.spain.friends.domain.user.model;

import es.adevinta.spain.friends.domain.common.AbstractDomainId;
import es.adevinta.spain.friends.domain.user.model.vo.Password;
import es.adevinta.spain.friends.domain.user.model.vo.Username;

public class UserId extends AbstractDomainId {

    protected UserId() {}

    private UserId(String value) {
        super(value);
    }

    public static UserId create(Username username, Password password) {
        return new UserId(username.value() + "." + password.value());
    }
}