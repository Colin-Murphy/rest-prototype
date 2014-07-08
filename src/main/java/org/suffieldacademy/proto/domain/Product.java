package org.suffieldacademy.proto.domain;

/**
	Internal class for representing a product in a database
	@author Colin Murphy <clm3888@rit.edu>
	@version 6/25/14
*/

//Xml stuff
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//OutputStream needed for Json output
import java.io.OutputStream;

//Throw these when writing to jason... maybe
import java.io.IOException;
import javax.ws.rs.WebApplicationException;

@XmlRootElement(name = "product")
public class Product {

	/**
		Plain text name of the item
	*/
	private String name;
	
	/**
		Internal product id number
	*/
	private int id;

	
	/**
		Price (in USD)
	*/
	private int price;
	
	/**
		Number available
	*/
	private int quantity;



	public Product() {
		//Required for 
	}
	
	/**
		Contstruct a new product with a given name, it, price and quantity
		This is only intended for early testing until I start reading from post correctly
		
		@param name Items name
		@param id the id
		@param price What it costs
		@param quantity how many there are
	*/
	
	public Product(String name, int id, int price, int quantity) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.quantity = quantity;
	}
	
	/**
		Get the product id
		@return The products id
	*/
	@XmlAttribute
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}
	
	/**
		Get the name of the product
		@returns the name
	*/
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	/**
		Set the name of the product
		@param name The new name
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/**
		Get the products price
		@return the price
	*/
	@XmlElement(name = "price")
	public int getPrice() {
		return price;
	}

	/**
		Setter for the price
	*/
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
		Get the quantity remaining
		@return The quantity
	*/
	@XmlElement(name = "quantity")
	public int getQuantity() {
		return quantity;
	}

	/**
		Setter for the price
	*/
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	

	/**
		Output to JSON
		@param os The output stream to write to
	
	public void writeTo(OutputStream os) throws IOException, WebApplicationException {
		//{"firstName":"John", "lastName":"Doe"}
		
		//Opening brace
		String output = "{\n";
		//ID for the product
		output +=		"\t\"id\": \"" + id + "\",\n";
		//Name of the product
		output +=		"\t\"name\": \"" + name + "\",\n";
		//Price
		output +=		"\t\"price\": \"" + price  +"\",\n";
		//Quantity
		output +=		"\t\"quantity\": \"" + quantity + "\"\n";
		//Closing brace
		output +=	"}";
		
		
		
		os.write(output.getBytes());
	
	
	}
	*/
	
	
	
	
	
}
