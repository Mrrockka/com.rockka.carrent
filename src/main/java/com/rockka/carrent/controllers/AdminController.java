package com.rockka.carrent.controllers;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController extends UserUtil {
	@Autowired
	private CarService carService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;

	private Logger logger = LoggerFactory.getLogger(AdminController.class);

	@GetMapping("/car/add_car")
	public String save() {
		return "admin/add_car";
	}

	@PostMapping("/car/save")
	@ResponseBody
	public String save(@RequestBody Car car) {
		String answer = "failure";
		try {
			carService.save(car);
			answer = "Success";
		} catch (Exception ex) {
			logger.error("Save exception " + ex);
		}
		return answer;
	}

	@GetMapping("/order/show_all")
	@ResponseBody
	public List<Order> showOrder() {
		List<Order> orders = orderService.getAll();
		for(Order order : orders){
			order.getCar().getName();
			order.getUser().getNickname();
		}
		return orders;
	}

	@GetMapping("/order/{id}")
	public String showOrder(@PathVariable("id") long id, Model model) {
		model.addAttribute("order", orderService.getById(id));
		return "user/order";
	}

	@GetMapping("/user/show_all")
	@ResponseBody
	public List<User> showUsers() {
		return userService.getAll();
	}

	@GetMapping("/user/{nickname}")
	public String showUser(@PathVariable("nickname") String nickname, Model model) {
		model.addAttribute("user", userService.getUserByNickname(nickname));
		return "admin/show_user";
	}

	@GetMapping("/car/show_all")
	@ResponseBody
	public List<Car> showCars(Model model) {
		return carService.getAll();
	}

	@GetMapping("/car/{id}")
	public String showCar(@PathVariable("id") long id, Model model) {
		model.addAttribute("role", "ROLE_ADMIN");
		model.addAttribute("car", carService.getById(id));
		return "public/show_car";
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
