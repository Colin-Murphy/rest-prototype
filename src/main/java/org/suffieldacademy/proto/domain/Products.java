package org.suffieldacademy.proto.domain;

import java.util.HashMap;
import java.util.Collection;

//Xml markup
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementRef;

//Throw these when writing to jason when things go wrong
import java.io.IOException;
import javax.ws.rs.WebApplicationException;

//OutputStream needed for Json output
import java.io.OutputStream;

import java.util.Iterator;

@XmlRootElement(name = "products")
public class Products {

	private Collection<Product> products;
	
	/**
		Empty constructor required, not used
	*/
	private Products() {
	
	}
	
	public Products(HashMap<Integer, Product> products) {
		this.products = products.values();
	}
	
	/**
		Get the xml for all the values in the collection
		Each product produces its own xml
	*/
	@XmlElementRef(name="products")
	public Collection<Product> getProducts() {
		return products;
	}
	
	
	/**
		Write all the products to json.
		Products are JsonWriteable, this method wraps the output of each Product in a json array
	
	public void writeTo(OutputStream os) throws IOException, WebApplicationException {
		os.write("{\n".getBytes());
		os.write("\"products\":[\n".getBytes());
		
		Iterator<Product> iterator = products.iterator();
		while(iterator.hasNext()) {
			Product p = iterator.next();
			p.writeTo(os);
			if (iterator.hasNext()) {
				os.write(",\n".getBytes());
			}
		}
		os.write("\n]\n".getBytes());
		os.write("}\n".getBytes());
	
	}

	*/

}
