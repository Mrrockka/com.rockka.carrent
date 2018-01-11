package com.rockka.carrent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/add_car")
    public String save(){
        return "admin/add_car";
    }

    @GetMapping("/account")
    public String account(){
        return "admin/account";
    }

}
