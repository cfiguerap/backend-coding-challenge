package es.adevinta.spain.friends.domain.common;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractDomainId implements Serializable {

    private String value;

    protected AbstractDomainId() {
    }

    protected AbstractDomainId(String value) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("Id must be not null");
        }
        this.value = value;
    }

    public String id() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDomainId id = (AbstractDomainId) o;
        return this.value.equals(id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}