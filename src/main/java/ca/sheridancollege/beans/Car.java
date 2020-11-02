package ca.sheridancollege.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car implements java.io.Serializable{
	
	private static final long serialVersionUID = -7196761359945076288L;
	
	private int id;
	private String make;
	private String model;
	private String colour;
	private Double price;
	private String VIN;
	
	private String dealership;
	
	private String[] dealerships = {"General Auto","City Auto","National Auto"};

	public Car(String make, String model, String colour, Double price, String VIN, String dealership) {
		this.make = make;
		this.model = model;
		this.colour = colour;
		this.price = price;
		this.VIN = VIN;
		this.dealership = dealership;
	}


}
