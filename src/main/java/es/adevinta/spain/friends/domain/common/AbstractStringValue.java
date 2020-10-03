package es.adevinta.spain.friends.domain.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractStringValue implements Serializable {

	private String value;

	protected AbstractStringValue() {
	}

	protected AbstractStringValue(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractStringValue id = (AbstractStringValue) o;
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