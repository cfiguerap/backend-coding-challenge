package es.adevinta.spain.friends.domain.user.model.vo;

import es.adevinta.spain.friends.domain.common.AbstractStringValue;

public class Password extends AbstractStringValue {

    protected Password() {}

    private Password(String value) {
        super(value);
    }

    public static Password create(String id) {
        return new Password(id);
    }
}