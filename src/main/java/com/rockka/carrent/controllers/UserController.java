package com.rockka.carrent.controllers;

import com.rockka.carrent.domain.User;
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
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String save(@RequestBody User user){
        String answer = "";
        logger.info("Before check");
        if(userService.isExists(user)){
            answer = "registration";
            logger.info("user is exists, answer is : " + answer);
        } else{
            user.setRoles("ROLE_USER");
            userService.save(user);
            answer = "user/account";
            logger.info("user not exists, answer is : " + answer);
        }
        return answer;
    }

    @GetMapping("/admin/user/get_all")
    public String getAll(Model model){
        model.addAttribute("users", userService.getAll());
        return "admin/show_users";
    }
}
