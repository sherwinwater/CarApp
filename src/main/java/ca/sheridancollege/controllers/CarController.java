package ca.sheridancollege.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.beans.Car;
import ca.sheridancollege.beans.Car;
import ca.sheridancollege.database.DatabaseAccess;


@Controller
public class CarController {

	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String goAddPage() {
		return "home.html";
		
	}
	
	@GetMapping("/add")
	public String addCar(Model model, @ModelAttribute Car car) {
		da.addCar(car);		
		model.addAttribute("car", new Car());

		return "add.html";
	}
	
	
	@GetMapping("/view")
	public String viewCars(Model model) {
		ArrayList<Car> cars = da.getCars();
		ArrayList<Car> carsA = da.getCarsBydealership("General Auto");
		ArrayList<Car> carsB = da.getCarsBydealership("City Auto");
		ArrayList<Car> carsC = da.getCarsBydealership("National Auto");
		model.addAttribute("cars",cars);
		model.addAttribute("carsA",carsA);
		model.addAttribute("carsB",carsB);
		model.addAttribute("carsC",carsC);
		
//		ArrayList<Car> carsALl = new ArrayList<>();
//		HashMap<String,ArrayList<Car>> myCars = new HashMap<String,ArrayList<Car>>();
//		myCars.put("A",carsA);
//		myCars.put("B",carsB);
//		myCars.put("C",carsC);
		
		return "view.html";
	}
	
	@GetMapping("/search")
	public String searchHome(Model model) {
				
		return "search.html";
	}
	
	@PostMapping("/search")
	public String searchCarByid(Model model,
								@RequestParam(required=false) String id,
								@RequestParam(required=false) String make,
								@RequestParam(required=false) String carModel,
								@RequestParam(required=false) String minPrice,
								@RequestParam(required=false) String maxPrice
								) {
		ArrayList<Car> cars = new ArrayList<>();
		if(id != null) {
			Car car = da.getCarById((Integer.parseInt(id)));
			cars.add(car);
		}
		if(make != null) {
			cars = da.getCarsByMake(make);
		}
		if(carModel != null) {
			cars = da.getCarsByModel(carModel);
		}
		if(minPrice !=null && maxPrice !=null) {
			cars = da.getCarsByPrice(Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
		}
		
		model.addAttribute("cars",cars);
		return "searchresult.html";
	}
	
	

	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable int id){
		Car d = da.getCarById(id);
		model.addAttribute("car", d);
		return "modify.html";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id){
		da.deleteCar(id);
		return "redirect:/view";
	}
	
	@GetMapping("/modify")
	public String modifyCar(Model model, @ModelAttribute Car car) {			
		da.editCar(car);
		return "redirect:/edit/"+car.getId();
	}
	
	@GetMapping("/purchase/{id}")
	public String purchaseItem(Model model, @PathVariable int id){
		Car d = da.getCarById(id);
		Double tax = d.getPrice() * 0.13;
		Double totalPrice = d.getPrice() + tax;
		model.addAttribute("car", d);
		model.addAttribute("tax",tax);
		model.addAttribute("totalPrice",totalPrice);
		model.addAttribute("localDateTime", LocalDateTime.now());

		da.deleteCar(id);
		return "receipt.html";
	}
	
	
	@GetMapping("/purchase")
	public String purchaseHomepage(){
		return "redirect:/view";
	}

}