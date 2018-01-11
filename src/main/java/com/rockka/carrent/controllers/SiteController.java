package com.rockka.carrent.controllers;

import com.rockka.carrent.dao.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {
    @Autowired
    private CarService carService;
    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("cars", carService.getAll());
        return "index";
    }

    @GetMapping("/info")
    public String info(){
        return "info";
    }

    @GetMapping("/registration")
    public String registratin(){
        return "registration";
    }
}
