package org.suffieldacademy.proto.services;

import org.suffieldacademy.proto.domain.JsonWriteable;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;



@Provider
@Produces("application/json")
public class JsonMarshaller implements MessageBodyWriter {


	/**
		This marshaller only handles objects that implement JsonWriteable.
	*/
	public boolean isWriteable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
	
		System.out.println(type.getName());
		return JsonWriteable.class.isAssignableFrom(type);
		//return true;
	}
	
	/**
		Not easilly calculatable, just return -1 and it will be calculated elsewhere.
	*/
	public long getSize(Object o, Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}
	
	/**
		Tell the object to write itself to the stream.
	*/
	public void writeTo(Object o, Class type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, OutputStream os) throws IOException, WebApplicationException {
	
		((JsonWriteable)o).writeTo(os);
	}




}
