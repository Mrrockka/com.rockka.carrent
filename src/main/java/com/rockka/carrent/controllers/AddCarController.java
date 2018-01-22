package com.rockka.carrent.controllers;

import com.rockka.carrent.domain.Car;
import com.rockka.carrent.services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
public class AddCarController {
	private Logger logger = LoggerFactory.getLogger(AddCarController.class);

	@Autowired
	private CarService carService;

	@GetMapping("/car/add_car")
	public String addCar() {
		return "admin/add_car";
	}

	@PostMapping("/car/save")
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
}
