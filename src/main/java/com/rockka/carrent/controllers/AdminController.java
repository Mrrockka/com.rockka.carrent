package com.rockka.carrent.controllers;

import com.rockka.carrent.domain.Car;
import com.rockka.carrent.services.CarOrderService;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarOrderService carOrderService;
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/account")
    public String account(){
        return "admin/account";
    }

    @GetMapping("/car/add_car")
    public String save(){
        return "admin/add_car";
    }

    @PostMapping("/car/save")
    public String save(@RequestBody Car car) {
        String answer = "failure";
        car.setModifiedAt(new Date()).setCreatedAt(new Date());
        try {
            carService.save(car);
            answer = "Success";
        } catch (Exception ex) {
            logger.error("Save exception " + ex);
        }
        return answer;
    }

    @GetMapping("/user/show")
    public String showUsers(Model model){
        model.addAttribute("users", userService.getAll());
        return "show_users";
    }

    @GetMapping("/order/show")
    public String showOrder(Model model){
//        model.addAttribute("orders", carOrderService.getAll());
        return "show_orders";
    }
}
