package org.suffieldacademy.proto.services;

import org.suffieldacademy.proto.domain.Product;
import org.suffieldacademy.proto.domain.Products;
import org.suffieldacademy.proto.domain.ProductDatabase;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;

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

}
