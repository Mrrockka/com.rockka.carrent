package com.rockka.carrent.controllers;

import com.rockka.carrent.services.CarService;
import com.rockka.carrent.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SiteController extends UserUtil{

    private Logger logger = LoggerFactory.getLogger(SiteController.class);

    @GetMapping("/info")
    public String info(){
        return "public/info";
    }

    @GetMapping("/username")
    public @ResponseBody String username(){
        String text = "unknown";
        UserDetails user = getPrincipal();

        if(user != null) {
            text = "{";
            String role = user.getAuthorities().toArray()[0].toString();
            text += "\"role\" : \"" + role+ "\", \"nickname\": \"" + user.getUsername() + "\"";
            text += "}";
        }
        return text;
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "public/login";
    }

    @RequestMapping("/access_denied")
    public String accessDenied(){
        return "public/access_denied";
    }

    @RequestMapping("/success")
    public String success(){
        return "public/success";
    }

}
