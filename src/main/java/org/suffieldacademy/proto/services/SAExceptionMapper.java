package org.suffieldacademy.proto.services;

import java.lang.Exception;
import javax.ws.rs.ext;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

@Provider
public class SAExceptionMapper implements ExceptionMapper<javax.ejb.EJBException> {

	Response toResponse(EJBException exception) {
		return Response.status(500).build();
	}
}
