package com.rockka.carrent.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.domain.Invoice;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.enums.CarStatus;
import com.rockka.carrent.enums.InvoiceStatus;
import com.rockka.carrent.enums.UserStatus;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.InvoiceService;
import com.rockka.carrent.services.UserService;
import com.rockka.carrent.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AdminAccountController extends UserUtil {
	@Autowired
	private CarService carService;
	@Autowired
	private UserService userService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private ObjectMapper mapper;

	private Logger logger = LoggerFactory.getLogger(AdminAccountController.class);

	@GetMapping("/invoice/show_all")
	@ResponseBody
	public JsonNode showInvoices() {
		ArrayNode node = mapper.createArrayNode();
		for(Invoice invoice: invoiceService.getAll()){
			if(invoice.getInvoiceStatus().toInt()>0) {
				node.addObject()
						.put("invoice_id", invoice.getId())
						.put("car_name", invoice.getCar().getName())
						.put("username", invoice.getUser().getUsername())
						.put("starts_at", invoice.getStartsAt().toString())
						.put("expires_at", invoice.getExpiresAt().toString())
						.put("price", invoice.getPrice())
						.put("status", invoice.getInvoiceStatus().toString());
			}
		}
		return node;
	}

	@GetMapping("/invoice/{id}")
    @ResponseBody
	public JsonNode showInvoicerById(@PathVariable("id") long id) {
	    Invoice invoice = invoiceService.getById(id);
	    ObjectNode node = mapper.createObjectNode();
		ArrayNode arrayNode = mapper.createArrayNode();

	    node.put("invoice_id", invoice.getId())
                .put("username", invoice.getUser().getUsername())
                .put("birthday", invoice.getUser().getBirthday().toString())
                .put("car_id", invoice.getCar().getId())
                .put("car_name", invoice.getCar().getName())
                .put("username", invoice.getUser().getUsername())
                .put("car_price", invoice.getCar().getPrice())
                .put("starts_at", invoice.getStartsAt().toString())
                .put("expires_at", invoice.getExpiresAt().toString())
                .put("invoice_price", invoice.getPrice())
                .put("description", invoice.getDescription())
                .put("status", invoice.getInvoiceStatus().toInt());

		node.set("statusValues", invoiceStatusValues());

	    return node;
	}

	@RequestMapping("/invoice/update/{id}/description/{description}/status/{status}")
	@ResponseBody
	public String updateInvoiceById(
			@PathVariable("id") long id
			, @PathVariable("description") String description
			, @PathVariable int status
	){
		String ans = "failure";

		Invoice invoice = invoiceService.getById(id);
		if(invoice != null) {
			invoiceService.update(invoice.setDescription(description).setInvoiceStatus(status));
			ans = "success";
		}

		return ans;
	}

	@GetMapping("/user/show_all")
	@ResponseBody
	public JsonNode showUsers() {
		ArrayNode node = mapper.createArrayNode();
		for(User user: userService.getAll()){
			node.addObject()
					.put("username", user.getUsername())
					.put("roles", user.getRoles())
					.put("firstname", user.getFirstName())
					.put("secondname", user.getSecondName())
                    .put("secondname", user.getSecondName())
					.put("birthday", user.getBirthday().toString())
					.put("address", user.getAddress())
					.put("status", user.getUserStatus().toString())
			;
		}
		return node;
	}

	@GetMapping("/user/{username}")
    @ResponseBody
	public JsonNode showByUsername(@PathVariable("username") String username) {
        User user = userService.getByUsername(username);
        ObjectNode node = mapper.createObjectNode();

        node.put("username", user.getUsername())
                .put("roles", user.getRoles())
                .put("firstname", user.getFirstName())
				.put("secondname", user.getSecondName())
				.put("lastname", user.getLastName())
				.put("birthday", user.getBirthday().toString())
				.put("address", user.getAddress())
                .put("about_me", user.getAboutMe())
                .put("status", user.getUserStatus().toString());

        return node;
	}

	@GetMapping("/car/show_all")
	@ResponseBody
	public JsonNode showCars(Model model) {
		ArrayNode node = mapper.createArrayNode();
		for(Car car: carService.getAll()){
			node.addObject()
					.put("car_id",car.getId())
					.put("name", car.getName())
					.put("color", car.getColor())
					.put("release_date", car.getReleaseDate().toString())
					.put("price", car.getPrice())
					.put("status", car.getCarStatus().toString())
			;
		}
		return node;
	}

	@GetMapping("/car/{id}")
    @ResponseBody
	public JsonNode showCarById(@PathVariable("id") long id) {
        Car car = carService.getById(id);
        ObjectNode node = mapper.createObjectNode();

		node.put("car_id",car.getId())
                .put("name", car.getName())
                .put("color", car.getColor())
                .put("release_date", car.getReleaseDate().toString())
                .put("price", car.getPrice())
				.put("description", car.getDescription())
                .put("status", car.getCarStatus().toInt());
		node.set("statusValues", carStatusValues());
		return node;
	}


    @RequestMapping("/car/save")
    @ResponseBody
    public String saveCar(@RequestBody Car car) {
        String answer = "failure";
        try {
            carService.save(car);
            answer = "Success";
        } catch (Exception ex) {
            logger.error("Save exception " + ex);
        }
        return answer;
    }

	@RequestMapping("/car/update/{id}/")
	@ResponseBody
	public String updateCar(@PathVariable("id") long id) {
		String answer = "failure";
		try {
			Car car = carService.getById(id);
			carService.update(car);
			answer = "Success";
		} catch (Exception ex) {
			logger.error("Save exception " + ex);
		}
		return answer;
	}

	@GetMapping("/account")
	public String account(){
		return "admin/account";
	}

	@GetMapping("/invoice/status_values")
	@ResponseBody
	public JsonNode invoiceStatusValues(){
		ArrayNode node = mapper.createArrayNode();
		for(InvoiceStatus status : InvoiceStatus.values()){
			node.addObject()
					.put("toInt", status.toInt())
					.put("toString", status.toString());
		}
		return node;
	}

	@GetMapping("/car/status_values")
	@ResponseBody
	public JsonNode carStatusValues(){
		ArrayNode node = mapper.createArrayNode();
		for(CarStatus status: CarStatus.values()){
			node.addObject()
					.put("toInt", status.toInt())
					.put("toString", status.toString());
		}
		return node;
	}

	@GetMapping("/user/status_values")
	@ResponseBody
	public JsonNode userStatusValues(){
		ArrayNode node = mapper.createArrayNode();

		for(UserStatus status : UserStatus.values()){
			node.addObject()
					.put("toInt", status.toInt())
					.put("toString", status.toString());
		}
		return node;
	}

	@RequestMapping("/invoice/save")
	@ResponseBody
	public String registerNewInvoice(@RequestBody ObjectNode node){
		String ans = "failure";

		Car car = carService.getById(node.get("car_id").asLong());
		User user = userService.getByUsername(node.get("username").asText());

//		TODO: debug date parsing
		Invoice invoice = new Invoice()
				.setCar(car)
				.setUser(user)
				.setDescription(node.get("description").asText())
				.setPrice(node.get("invoice_price").asDouble())
				.setStartsAt(new Date(node.get("on_date").asLong()))
				.setExpiresAt(new Date(node.get("on_date").asLong()))
				.setInvoiceStatus(node.get("status").asInt())
				;

		try {
			invoiceService.save(invoice);
			ans = "success";
		}catch(Exception ex){
			logger.error("" + ex);
		}

		return ans;
	}
}
