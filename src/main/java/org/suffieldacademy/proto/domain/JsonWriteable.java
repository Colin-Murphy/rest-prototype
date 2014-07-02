package org.suffieldacademy.proto.domain;

import java.io.OutputStream;
import java.io.IOException;
import javax.ws.rs.WebApplicationException;

/**
	An interface for objects that can be written to JSON
	
	Any objects to be written using the JsonMarshaller must implement this interface
	
	@author Colin Murphy <clm3888@rit.edu>
	@version 7/1/14
*/
public interface JsonWriteable {

	/**
		Write to the output stream.
		@param os The output stream to write to.
	*/
	public void writeTo(OutputStream os) throws IOException, WebApplicationException;

}
