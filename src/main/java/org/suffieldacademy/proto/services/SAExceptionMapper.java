package org.suffieldacademy.proto.services;

import java.lang.Throwable;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

//Errors with json/xml input
import javax.xml.bind.JAXBException;
import org.codehaus.jackson.JsonGenerationException;
import java.io.IOException;

//Not finding the requested product error
import javax.ws.rs.NotFoundException;

@Provider
public class SAExceptionMapper implements ExceptionMapper<Throwable> {

	public Response toResponse(Throwable exception) {

		if (exception instanceof NotFoundException) {
			return Response.status(Status.NOT_FOUND).build();
		}

		if (exception instanceof JsonGenerationException || exception instanceof JAXBException || exception instanceof IOException) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
