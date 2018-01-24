package com.rockka.carrent.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.domain.Order;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.OrderService;
import com.rockka.carrent.services.UserService;
import com.rockka.carrent.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminAccountController extends UserUtil {
	@Autowired
	private CarService carService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ObjectMapper mapper;

	private Logger logger = LoggerFactory.getLogger(AdminAccountController.class);

	@GetMapping("/order/show_all")
	@ResponseBody
	public JsonNode showOrders() {
		ArrayNode node = mapper.createArrayNode();
		for(Order order: orderService.getAll()){
			node.addObject()
					.put("order_id", order.getId())
					.put("car_name", order.getCar().getName())
					.put("username", order.getUser().getUsername())
					.put("starts_at", order.getStartsAt().toString())
					.put("expires_at", order.getExpiresAt().toString())
					.put("price", order.getPrice())
					.put("status", order.getOrderStatus().toString());
		}
		return node;
	}

	@GetMapping("/order/{id}")
    @ResponseBody
	public JsonNode showOrderById(@PathVariable("id") long id) {
	    Order order = orderService.getById(id);
	    ObjectNode node = mapper.createObjectNode();

	    node.put("order_id", order.getId())
                .put("username", order.getUser().getUsername())
                .put("birthday", order.getUser().getBirthday().toString())
                .put("car_name", order.getCar().getName())
                .put("car_price", order.getCar().getPrice())
                .put("starts_at", order.getStartsAt().toString())
                .put("expires_at", order.getExpiresAt().toString())
                .put("order_price", order.getPrice())
                .put("description", order.getDescription())
                .put("status", order.getOrderStatus().toString());

	    return node;
	}

	@RequestMapping("/order/update/{id}/description/{description}/status/{status}")
	@ResponseBody
	public String updateOrderWithId(
			@PathVariable("id") long id
			, @PathVariable("description") String description
			, @PathVariable int status
	){
		String ans = "failure";

		Order order = orderService.getById(id);
		if(order != null) {
			orderService.update(order.setDescription(description).setOrderStatus(status));
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
					.put("status", user.getUserStatus().toInt()==1? "active":"inactive")
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
                .put("birthday", user.getBirthday().toString())
                .put("firstname", user.getFirstName())
                .put("secondname", user.getSecondName())
                .put("lastname", user.getLastName())
                .put("address", user.getAddress())
                .put("about_me", user.getAboutMe())
                .put("status", user.getUserStatus().toInt()==1?"active":"inactive");

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
                .put("status", car.getCarStatus().toString());
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

	@GetMapping("/account")
	public String account(){
		return "admin/account";
	}

}
