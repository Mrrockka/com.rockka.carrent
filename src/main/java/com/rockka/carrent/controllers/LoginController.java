package com.rockka.carrent.controllers;

import com.rockka.carrent.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/welcome")
    public String Welcome(Model model){
        UserDetails user = getPrincipal();
        String page = "access_denied";

        if(user != null){

            String role = user.getAuthorities().toArray()[0].toString();
            model.addAttribute("user", user);
            model.addAttribute("user.role", role);

            switch (role){
                case "ROLE_ADMIN":
                    page = "admin/addcar";
                    break;
                case "ROLE_USER":
                    page = "/";
                    break;
                default:
                    break;
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
        return "login";
    }

    @RequestMapping("/access_denied")
    public String accessDenied(){
        return "access_denied";
    }

    private UserDetails getPrincipal(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails ?
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;
    }

}
