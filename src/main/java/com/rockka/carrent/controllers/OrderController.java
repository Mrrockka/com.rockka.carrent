package com.rockka.carrent.controllers;

import com.rockka.carrent.domain.Order;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.OrderService;
import com.rockka.carrent.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/car/{id}")
    public String getOrderForCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carService.getById(id));
        return "user/order";
    }

    @PostMapping("/save")
    public String save(@RequestBody Order order){
        return "success";
    }

    @GetMapping("/clean")
    public String clean(){
        return "user/order";
    }

}
