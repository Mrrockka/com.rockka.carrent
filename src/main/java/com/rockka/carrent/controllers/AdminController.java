package com.rockka.carrent.controllers;

import com.rockka.carrent.domain.Car;
import com.rockka.carrent.domain.Order;
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

import java.util.Date;
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
    public String save(){
        return "admin/add_car";
    }

    @PostMapping("/car/save")
    public @ResponseBody String save(@RequestBody Car car) {
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

    @GetMapping("/order/show_all")
    public @ResponseBody Map<String, String> showOrder(Model model){
        List<Order> orders = orderService.getAll();
        String jso = "";
        Map<String, String> json = new HashMap<String ,String>();
        for(Order order: orders){
            json.put("order_id", "" +order.getId());
            json.put("username", order.getUser().getNickname());
            json.put("car_name", order.getCar().getName());
            json.put("starts_at", order.getStartsAt().toString());
            json.put("expires_at", order.getExpiresAt().toString());
            json.put("price", "" + order.getPrice());
            json.put("status", order.getStatus());
        }
        return json;
    }

    @GetMapping("/order/{id}")
    public String showOrder(@PathVariable("id") long id, Model model){
        model.addAttribute("orders", orderService.getById(id));
        model.addAttribute("username", getPrincipal().getUsername());
        return "admin/show_order";
    }

    @GetMapping("/user/show_all")
    public String showUsers(Model model){
        model.addAttribute("users", userService.getAll());
        return "admin/show_users";
    }
    @GetMapping("/user/{nickname}")
    public String showUser(@PathVariable("nickname") String nickname, Model model){
        model.addAttribute("user", userService.getUserByNickname(nickname));
        return "admin/show_user";
    }

    @GetMapping("/car/show_all")
    public String showCars(Model model){
        model.addAttribute("cars", carService.getAll());
        return "admin/show_cars";
    }
    @GetMapping("/car/{id}")
    public String showCar(@PathVariable("id") long id, Model model){
        model.addAttribute("role", "ROLE_ADMIN");
        return "public/car";
    }
}
