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

//Not found
import javax.ws.rs.NotFoundException;

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
		@param id The id of the product to return
	*/
	@GET
	@Path("{id : \\d+}")
	@Produces({"application/xml","application/json"})
	public Product getProduct(@PathParam("id") int id) {
	
		Product p = db.get(id);
		if (p == null) {
			throw new NotFoundException();
		}
		return p;
	}


	/**
		Get a list of products.
		Search by adding ?search=query

		@param term An optional search term.
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
		Get a single product. Forced json output

		@param id The id of the product to return.
	*/
	@GET
	@Path("/json/{id : \\d+}")
	@Produces("application/json")
	public Product getProductJson(@PathParam("id") int id) {
		Product p = db.get(id);
		if (p == null) {
			throw new NotFoundException();
		}
		return p;
	}

	/**
		Get a list of products. Forced json output.
		Search by adding ?search=query

		@param term An optional search term.
	*/
	@GET
	@Path("/json")
	@Produces("application/json")
	public Products getProductsJson(@QueryParam("search") String term) {
		if (term !=null) {
			return db.search(term);
		}
		
		return db.getAll();
	}

	/**
		Delete a product from the database
		@param id The id of the product to delete
	*/
	@DELETE
	@Path("{id : \\d+}")
	public Response deleteProduct(@PathParam("id") int id) {

		Product p = db.get(id);
		if (p == null) {
			throw new NotFoundException();
		}
		db.remove(id);

		return Response.ok().build();
	}

	/**
		Do a delete from a standard get request, for demonstration purposes only
		@param id The id of the product to delete
	*/
	@GET
	@Path("/delete/{id : \\d+}")
	public Response deleteProductFromGet(@PathParam("id") int id) {
		
		Product p = db.get(id);
		//The error response could be handled here, but I let the exception mapper do it for consistant responses.
		if (p == null) {
			throw new NotFoundException();
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

	/**
		Modify an existing item in the database
		@param stream an XML stream containing any updated data (the id cannot be changed)
	*/
	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	@Path("{id : \\d+}")
	public Product editFromXml(InputStream stream, @PathParam("id") int id) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(org.suffieldacademy.proto.domain.Product.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Product p = (Product) jaxbUnmarshaller.unmarshal(stream);

		Product prod = db.get(id);
		if (prod == null) {
			throw new NotFoundException();
		}

		prod.merge(p);

		return prod;



	}

	/**
		Modify an existing item in the database
		@param stream an JSON stream containing any updated data (the id cannot be changed)
	*/
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("{id : \\d+}")
	public Product editFromJson(InputStream stream, @PathParam("id") int id) throws JsonGenerationException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		Product p = mapper.readValue(stream, Product.class);

		Product prod = db.get(id);
		if (prod == null) {
			throw new NotFoundException();
		}

		prod.merge(p);

		return prod;



	}



}
