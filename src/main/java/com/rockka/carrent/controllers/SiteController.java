package com.rockka.carrent.controllers;

import com.rockka.carrent.domain.User;
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

@Controller
public class SiteController {
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(SiteController.class);

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("cars", carService.getAll());
        return "public/index";
    }

    @GetMapping("/info")
    public String info(){
        return "public/info";
    }

    @GetMapping("/registration")
    public String registratin(){
        return "public/registration";
    }


    @PostMapping("/register")
    public String save(@RequestBody User user){
        String answer = "";

        if(userService.isExists(user)){
            answer = "exist";
            logger.debug("user is exists, answer is : " + answer);
        } else{
            user.setRoles("ROLE_USER");
            userService.save(user);
            answer = "welcome";
        }
        return answer;
    }
}
