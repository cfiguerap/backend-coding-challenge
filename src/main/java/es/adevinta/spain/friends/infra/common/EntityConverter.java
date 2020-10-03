package es.adevinta.spain.friends.infra.common;

import java.util.List;
import java.util.stream.Collectors;

public class EntityConverter<S,T> {

	public T target(S t) {
		throw new ConversionNotImplementedException();
	}

	public S source(T r) {
		throw new ConversionNotImplementedException();
	}

	public List<T> target(List<S> tList) {
		return tList.stream().map(this::target).collect(Collectors.toList());
	}

	public List<S> source(List<T> rList) {
		return rList.stream().map(this::source).collect(Collectors.toList());
	}
	
}