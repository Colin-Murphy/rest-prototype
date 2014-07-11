package org.suffieldacademy.proto.services;

import java.lang.Throwable;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

import javax.persistence.EntityNotFoundException;

import javax.ws.rs.core.Response.Status;

@Provider
public class SAExceptionMapper implements ExceptionMapper<Throwable> {

	public Response toResponse(Throwable exception) {

		if (exception instanceof EntityNotFoundException) {
			return Response.status(Status.NOT_FOUND).build();
		}

		else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
