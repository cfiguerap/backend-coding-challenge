package es.adevinta.spain.friends.domain.user.model;

import es.adevinta.spain.friends.domain.common.AbstractDomainId;

public class UserId extends AbstractDomainId {

    protected UserId() {}

    private UserId(String value) {
        super(value);
    }

    public static UserId create(String username, String password) {
        return new UserId(username + "." + password);
    }
}