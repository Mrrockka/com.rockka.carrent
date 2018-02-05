package com.rockka.carrent.controllers;

import com.rockka.carrent.dao.CarDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/*
** Controller for car view(public/show_car)
*/
@Controller
@RequestMapping("/car")
public class ShowCarController {
    @Autowired
    private CarDao carService;
    private Logger logger = LoggerFactory.getLogger(ShowCarController.class);
    /*
    ** Returns view with car info
    */
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carService.getById(id));
        return "public/show_car";
    }
}
