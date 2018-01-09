package com.rockka.carrent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/addcar")
    public String save(){
        return "admin/addcar";
    }

}
