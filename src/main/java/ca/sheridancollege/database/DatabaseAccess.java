package ca.sheridancollege.database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.beans.Car;


@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	
	public void addCar(Car Car) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query="INSERT INTO cars (make, model, colour, price,VIN,dealership) VALUES "+
			"(:make, :model, :colour, :price, :VIN, :dealership)";
		parameters.addValue("make", Car.getMake());
		parameters.addValue("model", Car.getModel());
		parameters.addValue("colour", Car.getColour());
		parameters.addValue("price", Car.getPrice());
		parameters.addValue("VIN", Car.getVIN());
		parameters.addValue("dealership", Car.getDealership());
		jdbc.update(query, parameters);
	}
	
	public void editCar(Car Car) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query="UPDATE cars SET make=:make, model=:model, colour=:colour,price=:price, VIN=:VIN, dealership=:dealership WHERE id=:id";
		parameters.addValue("id", Car.getId());
		parameters.addValue("make", Car.getMake());
		parameters.addValue("model", Car.getModel());
		parameters.addValue("colour", Car.getColour());
		parameters.addValue("price", Car.getPrice());
		parameters.addValue("VIN", Car.getVIN());
		parameters.addValue("dealership", Car.getDealership());
		jdbc.update(query, parameters);
	}
	
	public void deleteCar(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query="DELETE FROM cars  WHERE id=:id";
		parameters.addValue("id", id);

		jdbc.update(query, parameters);
	}
	
	public ArrayList<Car> getCars() {		
		String q = "Select * from cars";
		ArrayList<Car> Cars = 
				(ArrayList<Car>)jdbc.query(q,
				new BeanPropertyRowMapper<Car>(Car.class));
		return Cars;
	}
	
	public Car getCarById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM cars WHERE id=:id";
		parameters.addValue("id", id);
		ArrayList<Car> Cars = 
				(ArrayList<Car>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Car>(Car.class));
		if (Cars.size()>0)
			{System.out.println(Cars.get(0));
			return Cars.get(0);}
		return null;
	}
	
	public ArrayList<Car> getCarsBydealership(String dealership) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM cars WHERE upper(dealership)=upper(:dealership)";
		
		parameters.addValue("dealership", dealership);
		ArrayList<Car> Cars = 
				(ArrayList<Car>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Car>(Car.class));
		if (Cars.size()>0)
			{System.out.println(Cars.get(0));
			return Cars;}
		return null;
	}
	
	public ArrayList<Car> getCarsByMake(String make) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
//		String query = "SELECT * FROM cars WHERE upper(make)=upper(:make)";
		parameters.addValue("make", make);

		String query = "SELECT * FROM cars WHERE upper(make) LIKE  upper(:make) ";

		parameters.addValue("make", "%"+ make +"%");
		ArrayList<Car> Cars = 
				(ArrayList<Car>)jdbc.query(query, parameters,
						new BeanPropertyRowMapper<Car>(Car.class));
		if (Cars.size()>0)
		{System.out.println(Cars.get(0));
		return Cars;}
		return null;
	}
	
	public ArrayList<Car> getCarsByModel(String model) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
//		String query = "SELECT * FROM cars WHERE upper(model)=upper(:model)";
//		parameters.addValue("model", model);
		
		String query = "SELECT * FROM cars WHERE upper(model) LIKE upper(:model)";
		parameters.addValue("model", "%" + model + "%");
		
		ArrayList<Car> Cars = 
				(ArrayList<Car>)jdbc.query(query, parameters,
						new BeanPropertyRowMapper<Car>(Car.class));
		if (Cars.size()>0)
		{System.out.println(Cars.get(0));
		return Cars;}
		return null;
	}
	
	public ArrayList<Car> getCarsByPrice(double minPrice, double maxPrice) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM cars WHERE price BETWEEN :minPrice AND :maxPrice";
		parameters.addValue("minPrice", minPrice);
		parameters.addValue("maxPrice", maxPrice);
		ArrayList<Car> Cars = 
				(ArrayList<Car>)jdbc.query(query, parameters,
						new BeanPropertyRowMapper<Car>(Car.class));
		if (Cars.size()>0)
		{System.out.println(Cars.get(0));
		return Cars;}
		return null;
	}
	
	

}
