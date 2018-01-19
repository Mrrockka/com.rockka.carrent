package com.rockka.carrent.controllers;

import com.rockka.carrent.services.UserService;
import com.rockka.carrent.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController extends UserUtil {
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(){
        return "public/login";
    }

    @GetMapping("/welcome")
    public String Welcome(Model model){
        UserDetails user = getPrincipal();
        String page = "public/access_denied";

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
        logger.debug("/username output: " + text);
        return text;
    }


}
