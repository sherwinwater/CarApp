package ca.sheridancollege.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String addCarHome(Model model, @ModelAttribute Car car) {
		String msg ="";
		model.addAttribute("car", new Car());
		model.addAttribute("msg", msg);
		return "add.html";
	}
	
	@GetMapping("/addcar")
	public String addCar(Model model, @ModelAttribute Car car) {
		String msg ="";
		msg = da.addCar(car);		
		model.addAttribute("car", new Car());
		model.addAttribute("msg", msg);
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
		model.addAttribute("localDateTime", LocalDateTime.now());

		
		return "view.html";
	}
	
	
	@GetMapping("/search")
	public String searchHome(Model model) {
				
		return "searchcar.html";
	}
	
	@GetMapping("/searchcar")
	public String searchCarByid(Model model,
								@RequestParam(required=false) String id,
								@RequestParam(required=false) String make,
								@RequestParam(required=false) String carModel,
								@RequestParam(required=false) String minPrice,
								@RequestParam(required=false) String maxPrice,
								HttpSession session
								) {
		ArrayList<Car> cars = new ArrayList<>();
		if(id != null) {
			Car car = da.getCarById((Integer.parseInt(id)));
			cars.add(car);
			model.addAttribute("id",id);
		}
		if(make != null) {
			cars = da.getCarsByMake(make);
			model.addAttribute("make",make);

		}
		if(carModel != null) {
			cars = da.getCarsByModel(carModel);
			model.addAttribute("carModel",carModel);

		}
		if(minPrice !=null && maxPrice !=null) {
			cars = da.getCarsByPrice(Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
			model.addAttribute("minPrice",minPrice);
			model.addAttribute("maxPrice",maxPrice);

		}
		
		session.setAttribute("cars", cars);		
		model.addAttribute("cars",cars);
		model.addAttribute("search",true);
		model.addAttribute("localDateTime", LocalDateTime.now());
		
		System.out.println(cars);


		return "searchcar.html";
	}
	
	
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id){
		da.deleteCar(id);
		return "redirect:/view";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/delete/search/{id}")
	public String deleteSearch(Model model, @PathVariable int id,HttpSession session){
		da.deleteCar(id);
		
		ArrayList<Car> carlist = new ArrayList<>();
		carlist =(ArrayList<Car>) session.getAttribute("cars");
		
		Iterator<Car> iCar = carlist.iterator();
		while(iCar.hasNext()) {
			if (iCar.next().getId() == id) {
				iCar.remove();
			}
		}
		
		model.addAttribute("cars",carlist);
		model.addAttribute("search",true);
		model.addAttribute("localDateTime", LocalDateTime.now());
		
		return "searchcar.html";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable int id, HttpSession session){
		Car d = da.getCarById(id);
		model.addAttribute("car", d);
		
		
		return "modify.html";
	}
	
	@GetMapping("/modify")
	public String modifyCar(Model model, @ModelAttribute Car car, HttpSession session) {			
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
	public String purchaseHomepage(Model model){
		ArrayList<Car> cars = da.getCars();
		model.addAttribute("cars",cars);
		model.addAttribute("localDateTime", LocalDateTime.now());
			
		return "buy.html";
	}
	

}