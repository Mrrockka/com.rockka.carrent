package com.rockka.carrent.controllers;

import com.rockka.carrent.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserAccountController {
    private Logger logger = LoggerFactory.getLogger(UserAccountController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/account")
    public String account(Model model){
        return "user/account";
    }


}

