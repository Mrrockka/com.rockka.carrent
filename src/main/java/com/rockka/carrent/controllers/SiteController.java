package com.rockka.carrent.controllers;

import com.rockka.carrent.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {
    @Autowired
    private CarDao carDao;
    @RequestMapping("/")
    public String index(Model model){

        model.addAttribute("cars", carDao.getAll());
        return "index";
    }
    @RequestMapping("/addcar")
    public String save(){
        return "admin/addcar";
    }
    @RequestMapping("/login")
    public String signin(){
        return "login";
    }
    public CarDao getCarDao() {
        return carDao;
    }

    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }
}
