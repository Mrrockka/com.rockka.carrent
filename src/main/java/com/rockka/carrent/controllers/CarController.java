package com.rockka.carrent.controllers;

import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.domain.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarDao carService;
    private Logger logger = LoggerFactory.getLogger(CarController.class);

    @GetMapping("/get_all")
    public @ResponseBody List<Car> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") long id, Model model) {
        try{
            model.addAttribute("car", carService.getById(id));
        }catch(Exception ex){
            logger.error("" +ex);
        }
        return "public/car";
    }
}
