package com.rockka.carrent.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
					.put("status", order.getStatus());
		}
		return node;
	}

	@GetMapping("/order/{id}")
	public String showOrderById(@PathVariable("id") long id, Model model) {
		model.addAttribute("order", orderService.getById(id));
		return "admin/order";
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
					.put("birthday", user.getBirthday().toString())
					.put("address", user.getAddress())
					.put("status", user.getStatus()==0? "active":"inactive")
			;
		}
		return node;
	}

	@GetMapping("/user/{nickname}")
	public String showUserByNickname(@PathVariable("nickname") String nickname, Model model) {
		model.addAttribute("user", userService.getUserByUsername(nickname));
		return "admin/user";
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
					.put("status", car.getStatus()==0 ? "active" : "inactive")
			;
		}
		return node;
	}

	@GetMapping("/car/{id}")
	public String showCarById(@PathVariable("id") long id, Model model) {
		model.addAttribute("car", carService.getById(id));
		return "admin/car";
	}

    @GetMapping("/car/register_new_car")
    public String registerNewCar() {
        return "admin/register_new_car";
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

	@GetMapping("/settings")
	public String settings(){
		return "admin/settings";
	}
}
