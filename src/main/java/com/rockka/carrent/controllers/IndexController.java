package com.rockka.carrent.controllers;

import com.rockka.carrent.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@Autowired
	private CarService carService;

	@GetMapping("/")
	public String without(Model model){
		model.addAttribute("cars", carService.getAll());
		return "public/index";
	}

	@GetMapping("/index")
	public String index(Model model){
		return without(model);
	}

    @GetMapping("/home")
    public String home(Model model){
        return without(model);
    }

}