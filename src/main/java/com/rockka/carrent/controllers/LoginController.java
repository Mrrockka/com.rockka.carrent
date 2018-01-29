package com.rockka.carrent.controllers;

import com.rockka.carrent.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends UserUtil {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(){
        return "public/login";
    }

    @GetMapping("/account")
    public String Welcome(Model model){
        UserDetails user = getPrincipal();
        String page = "public/login";

        if(user != null){
            switch(user.getAuthorities().toArray()[0].toString()){
                case "ROLE_USER":
                    page = "user/account";
                    break;
                case "ROLE_ADMIN":
                    page = "admin/account";
                    break;
                default: break;
            }
        }
        return page;
    }

    @GetMapping("/registration")
    public String registratin(){
        return "public/registration";
    }

}
