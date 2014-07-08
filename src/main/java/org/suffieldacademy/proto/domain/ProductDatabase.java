package org.suffieldacademy.proto.domain;

import java.util.HashMap;
import java.util.Collection;


public class ProductDatabase {
	
	/**
		ArrayList of products to represent the database
	*/
	private HashMap<Integer, Product> products = new HashMap<Integer, Product>();

	private int nextID = 1;
	
	/**
		Create a new database and fill it with sample data
	*/
	public ProductDatabase() {
		
		//Hammer with id 1, costs $10 and there are 15 available
		Product p1 = new Product("Hammer", nextID++, 10, 15);
		
		products.put(p1.getID(), p1);
		
		products.put(2, new Product("Screwdriver", nextID++, 15, 100));
		
		products.put(3, new Product("Saw", nextID++, 50, 17));
		
	}
	
	
	
	/**
		Return a single product from the databse
		@param id The id of the product
		@return The product with the corresponding id or null
	*/
	public Product get(int id) {
		return products.get(id);
	}
	
	/**
		Get all the items in the database
		@return A collection of products that can be written to xml
	*/
	public Products getAll() {
		return new Products(products);
	}
	
	/**
		Find products by searching the name
		@param search The query to search names for
		@return The products with the search term in their name
	*/
	public Products search(String term) {
	
		HashMap<Integer, Product> results = new HashMap<Integer, Product>();
		
		for (Product p: products.values()) {

			if (p.getName().toLowerCase().contains(term.toLowerCase())){
				results.put(p.getID(), p);
			}
		}
		
		return new Products(results);
	
	}

	/**
		Remove an item from the database
		@param id: The id to remove
	*/
	public void remove(int id) {
			products.remove(id);
	}

	/**
		Add a new product to the database
		@param product The product to add (the id will be set here)
	*/
	public void add(Product p) {
		//Set the id and auto incriment
		p.setID(nextID++);
		products.put(p.getID(), p);
	}
}
