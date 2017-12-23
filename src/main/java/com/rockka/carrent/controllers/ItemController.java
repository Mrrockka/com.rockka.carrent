package com.rockka.carrent.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);
    @GetMapping("/header")
    public String header(){
        return "items/header.html";
    }

    @GetMapping("/footer")
    public String footer(){
        return "items/footer.html";
    }
    @GetMapping("/menu")
    public String menu(){
        return "items/menu.html";
    }
}
