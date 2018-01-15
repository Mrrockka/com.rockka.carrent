package com.rockka.carrent.controllers;

import com.rockka.carrent.domain.Car;
import com.rockka.carrent.services.OrderService;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

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

    @GetMapping("/user/show_all")
    public String showUsers(Model model){
        model.addAttribute("users", userService.getAll());
        return "admin/show_users";
    }

    @GetMapping("/order/show_all")
    public String showOrder(Model model){
        model.addAttribute("orders", orderService.getAll());
        return "admin/show_orders";
    }

    @GetMapping("/order/show/{id}")
    public String showOrder(@RequestParam("id") long id, Model model){
        model.addAttribute("order", orderService.getById(id));
        return "admin/show_order";
    }

    @GetMapping("/user/show/{nickname}")
    public String getByNickname(@RequestParam("nickname") String nickname, Model model){
        model.addAttribute("user", userService.getUserByNickname(nickname));
        return "admin/show_user";
    }
}
