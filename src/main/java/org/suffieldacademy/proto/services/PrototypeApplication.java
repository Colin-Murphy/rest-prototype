package org.suffieldacademy.proto.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/prototype")
public class PrototypeApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public PrototypeApplication() {
		singletons.add(new ProductResource());
		classes.add(JsonMarshaller.class);
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
	/**
	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
	*/
}
