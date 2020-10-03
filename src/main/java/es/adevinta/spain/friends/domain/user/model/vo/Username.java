package es.adevinta.spain.friends.domain.user.model.vo;


import es.adevinta.spain.friends.domain.common.AbstractStringValue;

public class Username extends AbstractStringValue {

    protected Username() {}

    private Username(String value) {
        super(value);
    }

    public static Username create(String id) {
        return new Username(id);
    }
}