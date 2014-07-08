package org.suffieldacademy.proto.services;

import org.suffieldacademy.proto.domain.Product;
import org.suffieldacademy.proto.domain.Products;
import org.suffieldacademy.proto.domain.ProductDatabase;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import java.io.InputStream;
import java.io.IOException;

//Product from xml
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

//Product from json
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@Path("/products")
public class ProductResource {

	ProductDatabase db = new ProductDatabase();
	
	
	/**
		Get a product from its id
	*/
	@GET
	@Path("{id : \\d+}")
	@Produces({"application/xml","application/json"})
	public Product getProduct(@PathParam("id") int id) {
	
		Product p = db.get(id);
		if (p == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return p;
	}


	/**
		Get a list of products.
		Search by adding ?search=query
	*/
	@GET
	@Produces({"application/xml","application/json"})
	public Products getProducts(@QueryParam("search") String term) {
		
		if (term !=null) {
			return db.search(term);
		}
		
		return db.getAll();
	}
	
	/**
		Same thing as above classes, but only return json
	*/

	@GET
	@Path("/json/{id : \\d+}")
	@Produces("application/json")
	public Product getProductJson(@PathParam("id") int id) {
		return db.get(id);
	}

	@GET
	@Path("/json")
	@Produces("application/json")
	public Products getProductsJson(@QueryParam("search") String term) {
		if (term !=null) {
			return db.search(term);
		}
		
		return db.getAll();
	}

	@DELETE
	@Path("{id : \\d+}")
	public Response deleteProduct(@PathParam("id") int id) {

		Product p = db.get(id);
		if (p == null) {
			return Response.ok().build();
		}
		db.remove(id);

		return Response.ok().build();
	}

	@GET
	@Path("/delete/{id : \\d+}")
	public Response deleteProductFromGet(@PathParam("id") int id) {
		
		Product p = db.get(id);
		if (p == null) {
			return Response.ok().build();
		}
		db.remove(id);

		return Response.ok().build();
	}

	/**
		Create a new product from an xml stream
		If an id is provided it will be overwritten when the database generates a new id
	*/
	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	public Product newProductFromXml(InputStream stream) throws JAXBException{

			JAXBContext jaxbContext = JAXBContext.newInstance(org.suffieldacademy.proto.domain.Product.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Product p = (Product) jaxbUnmarshaller.unmarshal(stream);

			db.add(p);

			return p;

	}

	/**
		Create a new product from a json stream
		If an id is provided it will be overwritten when the database generates a new id
	*/
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Product newProductFromJson(InputStream stream) throws JsonGenerationException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		Product p = mapper.readValue(stream, Product.class);

		db.add(p);

		return p;

	}



}
