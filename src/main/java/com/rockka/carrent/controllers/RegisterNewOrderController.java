package com.rockka.carrent.controllers;

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
@RequestMapping("/order")
public class RegisterNewOrderController extends UserUtil{
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    private Logger logger = LoggerFactory.getLogger(RegisterNewOrderController.class);

    @GetMapping("/car/{id}")
    public String getOrderForCar(@PathVariable("id") long id, Model model){
        User user =userService.getUserByUsername((getPrincipal()).getUsername());
        String username = user.getFirstName() + " " + user.getSecondName();
        username += user.getLastName() != null ? " " +user.getLastName() : "";
        model.addAttribute("nickname", username);
        model.addAttribute("user_address", user.getAddress());
        model.addAttribute("user_birthday", user.getBirthday());
        model.addAttribute("car", carService.getById(id));
        return "user/register_new_order";
    }

    @PostMapping("/save/{id}")
    public String save(@RequestBody Order order, @PathVariable("id") long id){
        order.setCar(carService.getById(id));
        order.setUser(userService.getUserByUsername(getPrincipal().getUsername()));
        order.setStatus(0);
        orderService.save(order);
        return "public/success";
    }

}
