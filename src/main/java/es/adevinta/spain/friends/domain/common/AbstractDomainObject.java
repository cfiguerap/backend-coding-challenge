package es.adevinta.spain.friends.domain.common;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractDomainObject<T extends AbstractDomainId> implements Serializable {

	private T id;

	protected AbstractDomainObject() {
		super();
	}

	public AbstractDomainObject(T id){
		if (StringUtils.isEmpty(id.toString())) {
			throw new IllegalArgumentException("Id must be not null");
		}
		this.id = id;
	}

	public T id(){
		return id;
	}

	@Override
	public String toString() {
		return id.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractDomainObject<?> that = (AbstractDomainObject<?>) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}